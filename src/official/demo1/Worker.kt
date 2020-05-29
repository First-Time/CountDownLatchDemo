package official.demo1

import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class Worker : Runnable {
    private var startSignal: CountDownLatch
    private var doneSignal: CountDownLatch

    constructor(startSignal: CountDownLatch, doneSignal: CountDownLatch) {
        this.startSignal = startSignal
        this.doneSignal = doneSignal
    }

    override fun run() {
        println("子线程doSomething ${Date().time}")
        startSignal.await()
        doWork()
        doneSignal.countDown()
    }

    private fun doWork() {
        println("doWork()方法被调用 ${Date().time}")
    }
}