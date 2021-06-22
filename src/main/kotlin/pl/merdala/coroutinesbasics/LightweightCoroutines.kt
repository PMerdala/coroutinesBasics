package pl.merdala.coroutinesbasics

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        repeat(1_000_000){
            launch {
                print(".")
            }
        }
    }
    println("After runBlocking")
}