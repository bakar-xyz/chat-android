package xyz.bakar.base

import android.os.Looper
import java.util.concurrent.Executors

/**
 * Created by kalapuneet on 24-12-2017.
 */
open class BaseExecutor {
    protected fun runOnBackgroundThread(toRun: Runnable) {
        if (Thread.currentThread() === Looper.getMainLooper().thread) THREADPOOL.submit(toRun) else toRun.run()
    }

    companion object {
        private val THREADPOOL = Executors.newCachedThreadPool()
    }
}