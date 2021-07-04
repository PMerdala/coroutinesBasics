package pl.merdala.coroutinesbasics

import kotlinx.coroutines.*

fun main(){
    /* jeżeli użyjemy zakresu ale nigdzie nie wywołamy job.join() ani deferredValue.await()
    to wyjątek jest przejmowany do momentu wykonania tych funkcji
     */
    runBlocking {
        val job:Job = GlobalScope.launch() {
            println("Throwing exception from job")
            throw RuntimeException("Runtime Exception job")
        }
//        job.join();

        val deferredValue:Deferred<Int> = GlobalScope.async {
            println("Throwing exception from async")
            throw RuntimeException("Runtime Exception async")
            1
        }
//        val value:Int = deferredValue.await()
    }
}