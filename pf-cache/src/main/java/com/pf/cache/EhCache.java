package com.pf.cache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.*;
import net.sf.ehcache.distribution.RMIBootstrapCacheLoaderFactory;
import net.sf.ehcache.distribution.RMICacheManagerPeerListenerFactory;
import net.sf.ehcache.distribution.RMICacheManagerPeerProviderFactory;
import net.sf.ehcache.distribution.RMICacheReplicatorFactory;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Author: Ru He
 * Date: Created in 2019/1/21.
 * Description:
 */
//@org.springframework.context.annotation.Configuration
//@EnableCaching
public class EhCache {

    private final static Logger log = LoggerFactory.getLogger(EhCache.class);

    private static CacheManager cacheManager;

    private EhCache(){
        log.info("[Ehcache配置初始化<开始>]");

        Configuration conf = new Configuration()
                //设置临时文件目录
                .diskStore(new DiskStoreConfiguration().path("java.io.temp"))
                //指定除自身之外的网络群体中其他提供同步的主机列表，用“|”分开不同的主机
                .cacheManagerPeerProviderFactory(new FactoryConfiguration()
                        .className(RMICacheManagerPeerProviderFactory.class.getName())
                        .properties("peerDiscovery=manual,rmiUrls=//localhost:40001/cache_user|//localhost:40002/cache_user"))
                //配宿主主机配置监听程序
                .cacheManagerPeerListenerFactory(new FactoryConfiguration()
                        .className(RMICacheManagerPeerListenerFactory.class.getName())
                        .properties("port=40003,socketTimeoutMillis=2000"))
                .cache(new CacheConfiguration("cache_user", 10000)//缓存名称(必须唯一),maxElements内存最多可以存放的元素的数量
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)//清理机制：LRU最近最少使用 FIFO先进先出 LFU较少使用
                        .timeToIdleSeconds(1000)//元素最大闲置时间
                        .timeToLiveSeconds(2000)//元素最大生存时间
                        .diskExpiryThreadIntervalSeconds(120)//缓存清理时间(默认120秒)
                        //LOCALTEMPSWAP当缓存容量达到上限时，将缓存对象（包含堆和非堆中的）交换到磁盘中
                        //NONE当缓存容量达到上限时，将缓存对象（包含堆和非堆中的）交换到磁盘中
                        //DISTRIBUTED按照_terracotta标签配置的持久化方式执行。非分布式部署时，此选项不可用
                        .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.NONE)).maxEntriesLocalDisk(0)//磁盘中最大缓存对象数0表示无穷大
                        .cacheEventListenerFactory(new CacheConfiguration.CacheEventListenerFactoryConfiguration()
                                .className(RMICacheReplicatorFactory.class.getName()).properties(
                                        "replicateAsynchronously=false,replicatePuts=true,replicatePutsViaCopy=true,replicateUpdates=true,replicateUpdatesViaCopy=true,replicateRemovals=true"))
                        .bootstrapCacheLoaderFactory(new CacheConfiguration.BootstrapCacheLoaderFactoryConfiguration().className(RMIBootstrapCacheLoaderFactory.class.getName()))
                );


        cacheManager = CacheManager.create(conf);
        log.info("[Ehcache配置初始化<完成>]");
    }

    public static String getCache(String key){
        Element ele = cacheManager.getCache("cache_user").get(key);
        return (String)ele.getObjectValue();
    }

    public static void setCache(String key, String val){
        Element ele = new Element(key, val);
        cacheManager.getCache("cache_user").put(ele);
    }

    public static void createCacheManager(){
        new EhCache();
    }

}
