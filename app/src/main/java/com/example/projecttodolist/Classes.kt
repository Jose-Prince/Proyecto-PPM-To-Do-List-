package com.example.projecttodolist

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

data class Tarea(
    var type : String,
    var name : String,
    var dateI : String,
    var dateF : String,
    var timeI : String,
    var timeF : String,
    var duration : Long
)

data class Month(
    var name: String,
    var year: Int,
    var i: Int
) {
    var numOfDays: Int = when (name) {
        "Enero" -> 31
        "Febrero" -> if (((year % 400).toInt() == 0) || ((year % 4).toInt() == 0 && (year % 100).toInt() != 0)) 29 else 28
        "Marzo" -> 31
        "Abril" -> 30
        "Mayo" -> 31
        "Junio" -> 30
        "Julio" -> 31
        "Agosto" -> 31
        "Septiembre" -> 30
        "Octubre" -> 31
        "Noviembre" -> 30
        else -> 31
    }

    val monthNumber = when (name){
        "Enero" -> 1
        "Febrero" -> 2
        "Marzo" -> 3
        "Abril" -> 4
        "Mayo" -> 5
        "Junio" -> 6
        "Julio" -> 7
        "Agosto" -> 8
        "Septiembre" -> 9
        "Octubre" -> 10
        "Noviembre" -> 11
        else -> 12
    }

    val initialDay : String = CalculateInDay()

    private fun CalculateInDay() : String{
        val firstDay = LocalDate.of(year, java.time.Month.of(monthNumber), 1)
        val dayName = firstDay.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("es", "ES"))
        return dayName.substring(0, 2).uppercase()
    }

}
