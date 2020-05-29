package common

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

/**
 * 在子线程中调用await
 *
 * 所有子线程初始化完成后，再并发执行所有子线程的后续任务
 */
fun main() {
    val pool = Executors.newCachedThreadPool()
    val cdl = CountDownLatch(100)
    for (i in 1..100) {
        val runnable = CountRunnable(cdl)
        pool.execute(runnable)
    }
    pool.shutdown()
}

class CountRunnable(private val countDownLatch: CountDownLatch) : Runnable {

    override fun run() {
        synchronized(countDownLatch) {
            println("thread count = ${countDownLatch.count}")
            //每次减少一个容量
            countDownLatch.countDown()
        }
        countDownLatch.await()
        println("concurrency counts = ${100 - countDownLatch.count}")
    }

}