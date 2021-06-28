package pl.merdala.coroutinesbasics

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        val job1: Job = launch {
//            delay(3000L)
            println("Job1 is launching")

            val job2: Job = launch{
                println("Job2 is launching")
                delay (500L)
                println("Job2 finished")
            }
            job2.invokeOnCompletion { println ("Job2 completed") }
            val job3: Job = launch{
                println("Job3 is launching")
                delay (1500L)
                println("Job3 finished")
            }
            job3.invokeOnCompletion { println ("Job3 completed") }
        }
        job1.invokeOnCompletion { println("Job1 completed") }
        delay(1000L)
        println("Job1 will be cancelled")
        job1.cancel()
    }
}