package pl.merdala.coroutinesbasics

import kotlinx.coroutines.*

fun main(){
    runBlocking {
        launch(Dispatchers.Default){
            delay(500L)
            println("First context: $coroutineContext")
            withContext(Dispatchers.IO){
                delay(1500L)
                println("Second context: $coroutineContext")
            }
            delay(500L)
            println("Third context: $coroutineContext")
        }
        println("end runBlocking")
    }
}