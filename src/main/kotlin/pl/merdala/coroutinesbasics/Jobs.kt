package pl.merdala.coroutinesbasics

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        val job1: Job = launch {
            delay(3000L)
            println("Job1 launched")
        }
        job1.invokeOnCompletion { println("Job1 completed") }
        delay(500L)
        println("Job1 will be cancelled")
        job1.cancel()
    }
}