package pl.merdala.coroutinesbasics

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        launch(CoroutineName("myCoroutine")) {
            println("This is run form ${coroutineContext.get(CoroutineName.Key)}")
        }
    }
}