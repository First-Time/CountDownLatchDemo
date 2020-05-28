package common

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class CountDownLatchDemo {
    fun test() {
        val latch = CountDownLatch(2)
        println("主线程开始执行...")
        //第一个子线程执行
        val es1 = Executors.newSingleThreadExecutor()
        es1.execute {
            Thread.sleep(3000)
            println("子线程：${Thread.currentThread().name}执行")
            latch.countDown()
        }
        es1.shutdown()

        val es2 = Executors.newSingleThreadExecutor()
        es2.execute {
            Thread.sleep(5000)
            println("子线程：${Thread.currentThread().name}执行")
            latch.countDown()
        }
        es2.shutdown()
        println("等待两个线程执行完毕")

        latch.await()
        println("两个子线程都执行完毕，继续执行主线程")
    }

}

fun main() {
    val countDownLatchDemo = CountDownLatchDemo()
    countDownLatchDemo.test()
}