package com.hr;

import net.sf.ehcache.config.ConfigurationHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableCaching
@Configuration
public class App
{

    /*@Autowired
    private EhCacheCacheManager EhCacheCacheManager;

    @Bean
    public EhCacheCacheManager cacheManager(){
        EhCacheCacheManager b = new EhCacheCacheManager();
        return b;
    }*/

    @Bean
    public ConfigurationHelper configurationHelper(){
        return null;
    }

    /*@Bean
    public RMICacheManagerPeerListenerFactory cacheManagerPeerListenerFactory(){
        RMICacheManagerPeerListenerFactory factory = new RMICacheManagerPeerListenerFactory();
        Properties p = new Properties();
        p.setProperty("hostName", "127.0.0.1");
        p.setProperty("port", "40002");
        factory.createCachePeerListener(EhCacheCacheManager.getCacheManager(), p);
        return factory;
    }*/

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
