package official.demo1

import java.util.concurrent.CountDownLatch

/**
 * 在子线程中调用await
 *
 * 多个子线程都初始化完成后，再一起继续执行多个子线程的后续任务
 */
fun main() {
    val startSignal = CountDownLatch(1)
    val doneSignal = CountDownLatch(10)
    for (i in 1..10) {
        Thread(Worker(startSignal, doneSignal)).start()
    }
//    println("doSomethingElse") //在此处执行的代码与子线程并发执行，不能保证在多个子线程执行完成后执行
    startSignal.countDown()
    /*for (i in 1..10000) {
        println("alsoDoSomethingElse")
    }*/
    doneSignal.await()
    println("onceMoreDoSomethingElse")
}