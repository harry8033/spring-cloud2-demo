package com.pf.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author: Ru He
 * Date: Created in 2019/1/29.
 * Description: 异步任务配置类
 */
@EnableAsync
@Configuration
public class AsyncTaskConfig {

    private static final int CORE_POOL_SIZE = 8;       		         // 核心线程数（默认线程数）
    private static final int MAX_POOL_SIZE = 40;			             // 最大线程数
    private static final int KEEP_ALIVE_TIME = 10;			         // 允许线程空闲时间（单位：默认为秒）
    private static final int QUEUE_CAPACITY = 200;			         // 缓冲队列数
    private static final String THREAD_NAME_PREFIX = "Async-Service-"; // 线程池名前缀

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        // 线程池对拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
