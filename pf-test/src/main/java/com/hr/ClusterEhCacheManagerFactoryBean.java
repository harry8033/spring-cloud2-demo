package com.hr;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.CacheManager;

/**
 * Author: Ru He
 * Date: Created in 2019/1/17.
 * Description:
 */
public class ClusterEhCacheManagerFactoryBean implements FactoryBean<CacheManager>, InitializingBean, DisposableBean {
    @Override
    public void destroy() throws Exception {

    }

    @Override
    public CacheManager getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
