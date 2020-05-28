package common

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

fun main() {
    val pool = Executors.newCachedThreadPool()
    val cdl = CountDownLatch(100)
    for (i in 1..100) {
        val runnable = CountRunnable(cdl)
        pool.execute(runnable)
    }
}

class CountRunnable(private val countDownLatch: CountDownLatch) : Runnable {

    override fun run() {
        synchronized(countDownLatch) {
            //每次减少一个容量
            countDownLatch.countDown()
            println("thread count = ${countDownLatch.count}")
        }
        countDownLatch.await()
        println("concurrency counts = ${100 - countDownLatch.count}")
    }

}