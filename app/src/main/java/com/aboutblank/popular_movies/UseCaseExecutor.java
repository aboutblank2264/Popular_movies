package com.aboutblank.popular_movies;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Class that queues and executes usecases.
 * Simple implementation, allocating threadpool.
 */
public class UseCaseExecutor {
    private static UseCaseExecutor instance;

    private final int CORE_POOL_SIZE = 3;
    private final int MAX_POOL_SIZE = 5;
    private final int KEEP_ALIVE_TIME = 120;
    private final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private final BlockingQueue<Runnable> BLOCKING_QUEUE = new LinkedBlockingQueue<>(MAX_POOL_SIZE);

    private ThreadPoolExecutor executor;

    public UseCaseExecutor() {
        this.executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TIME_UNIT,
                BLOCKING_QUEUE);
    }

    public static UseCaseExecutor getInstance() {
        if(instance == null) {
            instance = new UseCaseExecutor();
        }

        return instance;
    }

    public <T extends UseCase.RequestValue, R extends UseCase.ResponseValue> void execute(
            final UseCase<T, R> useCase,
            T requestValue,
            UseCase.CallBack<R> callback) {
        useCase.setRequestValue(requestValue);
        useCase.setCallBack(callback);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                useCase.run();
            }
        });
    }
}
