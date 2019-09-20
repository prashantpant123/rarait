package com.rarait.education.config;

import com.rarait.education.shared.AppProperties;
import com.rarait.framework.config.BaseAsynchronousConfig;
import com.rarait.framework.shared.ProfileNames;
import com.rarait.framework.shared.resource.ThreadPoolResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

/**
 * <p>The executor adjust the pool size based on core and max value.
 * If during task submission, the threads running are lesser than core size
 * then new threads are created. If the threads are equal or greater then task are queued.
 * If queue limit is reached, then new threads are created till max size.
 * After reaching max size, all new submitted task are rejected.
 * </p>
 *
 * @author Prajin Maharjan
 * @since 1.0
 */
@Slf4j
@EnableAsync
@Configuration
@Profile(ProfileNames.PROD)
@PropertySource(value = AppProperties.PROP_PATH, ignoreResourceNotFound = true)
public class AsyncConfiguration extends BaseAsynchronousConfig implements AsyncConfigurer {

    @Value(AppProperties.ASYNC_POOL)
    private final int CORE_SIZE = 10;

    @Value(AppProperties.QUEUE_POOL)
    private final int QUEUE_SIZE = 10;

    @Value(AppProperties.MAX_POOL)
    private final int MAX_POOL_SIZE = 10;

    @Override
    public Executor getAsyncExecutor() {
        return getExecutor(ThreadPoolResource.builder()
                .name("education")
                .coreSize(CORE_SIZE)
                .queueSize(QUEUE_SIZE)
                .maxCapacity(MAX_POOL_SIZE)
                .build());
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
