package com.pf.cache;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Author: Ru He
 * Date: Created in 2019/1/21.
 * Description:
 */
@Component
public class RunListener implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        EhCache.createCacheManager();
    }
}
