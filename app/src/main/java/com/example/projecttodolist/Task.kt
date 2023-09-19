package com.example.projecttodolist

import java.time.LocalDate
import kotlin.random.Random


class Tarea  {
    var id : String = ""
    var name : String = ""
    var date : String = ""
    var time : String = ""

}

fun idCreator(): String{
    val banco : String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"

    var string : String = ""
    for (x in 0 until 8) {
        val randomIndex = numeroAleatorioEnRango(0, banco.length - 1)
        val randomChar = banco[randomIndex]
        string += randomChar
    }
    return string
}

fun numeroAleatorioEnRango(min: Int, max: Int) : Int {
    return Random.nextInt(min, max + 1)
}