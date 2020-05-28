package official.demo2

import java.util.concurrent.CountDownLatch

class WorkerRunnable : Runnable {
    private var doneSignal: CountDownLatch
    private var i: Int

    constructor(doneSignal: CountDownLatch, i: Int) {
        this.doneSignal = doneSignal
        this.i = i
    }

    override fun run() {
        doWork(i)
        doneSignal.countDown()
    }

    private fun doWork(num: Int) {
        println("线程${num}的doWork()方法被调用 ${Thread.currentThread().name}")
    }
}