package official.demo2

import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

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