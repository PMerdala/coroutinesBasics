package pl.merdala.coroutinesbasics

import kotlinx.coroutines.*

fun main() {
    runBlocking() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception Handles ${throwable.localizedMessage}")
        }
        val job: Job = CoroutineScope(newSingleThreadContext("t1"))
            .launch(Dispatchers.Default + exceptionHandler) {
                val job1: Job = launch(Dispatchers.IO) {
                    delay(2000L)
                    println("Finished sub job1")
                }
                val job3: Job = launch(Dispatchers.IO) {
                    delay(1000L)
                    println("Finished sub job3")
                }
                val job2: Job = launch(Dispatchers.Unconfined) {
                    delay(1500L)
                    throw RuntimeException("Throw job2 RuntimeException")
                }
                delay(500L)
                println("Finished job main");
            }
        job.join()

        val deferredValue :Deferred<Int> = CoroutineScope(newSingleThreadContext("async"))
            .async(Dispatchers.IO) {getValue()}

        delay(600)
        try{
            val value = deferredValue.await()
        }catch (e:Exception){
            println("catch await exception: ${e.localizedMessage}")
        }

        println("End runBlocking")
    }
    println("End fun main")
}

suspend fun getValue():Int{
    throw RuntimeException("wyjątek w trakcie async dla Dispatchers.IO")
    return 1
}