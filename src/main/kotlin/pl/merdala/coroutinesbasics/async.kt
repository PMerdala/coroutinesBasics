package pl.merdala.coroutinesbasics

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main(){
    runBlocking {
        println("started runBlocking")
        val firstDeferred : Deferred<Int> = async{ getFirstValue()}
        val secondDeferred:Deferred<Int> = async { getSecondValue() }
        delay(500L)
        println("Waiting for values")
        val firstValue:Int = firstDeferred.await()
        val secondValue:Int = secondDeferred.await()
        println("The total is ${firstValue+secondValue}")
    }
    println("End main")
}

suspend fun getFirstValue():Int{
    println("start getFirstValue")
    delay(1000L)
    val value:Int = Random.nextInt(100)
    println("Returning first value $value")
    return value
}

suspend fun getSecondValue():Int{
    println("start getSecondValue")
    delay(2000L)
    val value:Int = Random.nextInt(1000)
    println("Returning second value $value")
    return value
}