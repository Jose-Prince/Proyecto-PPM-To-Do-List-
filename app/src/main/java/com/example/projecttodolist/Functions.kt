package com.example.projecttodolist

import java.text.SimpleDateFormat
import kotlin.random.Random

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

fun DateOrganizer(date : String) {
    var i : Int = 0
    val format = SimpleDateFormat("dd/MM/yyyy")
    try {
        if (GlobalVariables.listOfDates.size == 0)
            GlobalVariables.listOfDates.add(format.parse(date))
        else {
            for (day in GlobalVariables.listOfDates){
                if (day == format.parse(date))
                    i++
            }

            if (i == 0)
                GlobalVariables.listOfDates.add(format.parse(date))
        }

        GlobalVariables.listOfDates.sortedByDescending { it }

    } catch (ex: Exception) {
        println("Error al convertir la cadena a Date: ${ex.message}")
    }
}

fun NameAdjust(name : String) : String {
    if (name.length > 12) {
        var nombre = name.substring(0..11) + "..."
        return nombre
    } else {
        return name
    }
}