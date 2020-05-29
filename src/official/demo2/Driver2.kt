package official.demo2

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

/**
 * 在主线程中调用await
 *
 * 多个子线程都执行完成后，再继续执行主线程
 */
fun main() {
    var doneSignal = CountDownLatch(10)
    var e = Executors.newSingleThreadExecutor()
    for (i in 1..10) {
        e.execute(WorkerRunnable(doneSignal, i))
    }
    println("doSomethingElse")
    doneSignal.await()
    println("doSomethingElseAgain")
}