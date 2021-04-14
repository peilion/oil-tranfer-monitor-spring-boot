package cn.peilion.common.threadpool;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;


@Data
@Configuration
@EnableAsync
public class ThreadPoolConfig {
    // 核心线程数（默认线程数）
    private static final int CORE_POOL_SIZE = 10;
    // 最大线程数
    private static final int MAX_POOL_SIZE = 100;
    // 允许线程空闲时间（单位：默认为秒）
    private static final int KEEP_ALIVE_TIME = 10;
    // 缓冲队列数
    private static final int QUEUE_CAPACITY = 500;
    // 线程池名前缀
    private static final String THREAD_NAME_PREFIX = "default-async-";
    @Value("${spring.application.name:test}")
    String applicationName;

    /**
     * 默认异步线程池
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix(THREAD_NAME_PREFIX + getApplicationName());
        pool.setCorePoolSize(CORE_POOL_SIZE);
        pool.setMaxPoolSize(MAX_POOL_SIZE);
        pool.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        pool.setQueueCapacity(QUEUE_CAPACITY);
        // 直接在execute方法的调用线程中运行
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        pool.initialize();
        return pool;
    }

}
