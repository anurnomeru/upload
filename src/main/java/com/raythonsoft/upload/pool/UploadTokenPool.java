package com.raythonsoft.upload.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created by Anur IjuoKaruKas on 2017/11/17.
 * Description :
 */
public class UploadTokenPool {

    private static final int corePoolSize = 600;
    private static final int maximumPoolSize = 600;

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("UploadTokenDestroyerPool - %d -")
            .build();

    private static ExecutorService uploadTokenDestroyerPool = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            1,
            TimeUnit.MINUTES,
            new SynchronousQueue<>(),
            threadFactory,
            new ThreadPoolExecutor.AbortPolicy());

    public static ExecutorService getPool() {
        return uploadTokenDestroyerPool;
    }
}
