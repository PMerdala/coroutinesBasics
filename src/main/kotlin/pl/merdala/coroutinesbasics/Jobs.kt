package pl.merdala.coroutinesbasics

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        val job1: Job = launch {
            println("Job1 launched")
        }
        job1.invokeOnCompletion { println("Job1 completed") }
    }
}