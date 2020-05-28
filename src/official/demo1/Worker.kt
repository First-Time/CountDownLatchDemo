package official.demo1

import java.util.concurrent.CountDownLatch

class Worker : Runnable {
    private var startSignal: CountDownLatch
    private var doneSignal: CountDownLatch

    constructor(startSignal: CountDownLatch, doneSignal: CountDownLatch) {
        this.startSignal = startSignal
        this.doneSignal = doneSignal
    }

    override fun run() {
        startSignal.await()
        doWork()
        doneSignal.countDown()
    }

    private fun doWork() {
        println("doWork()方法被调用")
    }
}