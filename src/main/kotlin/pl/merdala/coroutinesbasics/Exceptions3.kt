package pl.merdala.coroutinesbasics

import kotlinx.coroutines.*

fun main() {
    runBlocking() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception Handles ${throwable.localizedMessage}")
        }
        /* jeżeli wywołujemy launch bez zakresu np.GlobalScope.launch nie działa obsługa wyjątków przez
        CoroutineExceptionHandle działa tylko obsługa wyjątku wewnątrz coroutines
         */
        val job: Job = launch(Dispatchers.Default + exceptionHandler) {
            println("Job CoroutineContext ${coroutineContext.toString()}")
            val job1: Job = launch(Dispatchers.IO) {
                delay(2000L)
                println("Finished sub job1")
            }
            val job3: Job = launch(Dispatchers.IO) {
                println("Job3 CoroutineContext ${coroutineContext.toString()}")
                delay(1000L)
                println("Finished sub job3")
            }
            val job2: Job = launch(Dispatchers.Unconfined) {
                println("Job2 CoroutineContext ${coroutineContext.toString()}")
                delay(1500L)
                try {
                    throw RuntimeException("Throw job2 RuntimeException")
                } catch (e: RuntimeException) {
                    println("wyjątek obsłużony w bloku try catch: ${e.localizedMessage}")
                }
            }
            delay(500L)
            println("Finished job main");
        }

        /* jeżeli wywołujemy async bez zakresu np.GlobalScope.async nie
        wyjątek jest wysyłany natychmiast a nie w trakcie wykonania await()
        działa tylko obsługa wyjątku wewnątrz coroutines
         */
        val deferredValue = async(Dispatchers.IO) {
            delay(600)
            try{
            throw java.lang.RuntimeException("Throw deferredValue")
            } catch (e: RuntimeException) {
                println("wyjątek obsłużony w bloku try catch: ${e.localizedMessage}")
            }
        }

        println("End runBlocking")
    }
    println("End fun main")
}