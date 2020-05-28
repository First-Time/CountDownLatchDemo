package official.demo1

import java.util.concurrent.CountDownLatch

fun main() {
    val startSignal = CountDownLatch(1)
    val doneSignal = CountDownLatch(10)
    for (i in 1..10) {
        Thread(Worker(startSignal, doneSignal)).start()
    }
    println("doSomethingElse")
    startSignal.countDown()
    for (i in 1..10000) {
        println("alsoDoSomethingElse")
    }
    doneSignal.await()
    println("onceMoreDoSomethingElse")
}