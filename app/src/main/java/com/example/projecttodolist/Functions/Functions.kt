package com.example.projecttodolist.Functions

import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Tarea
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun NameAdjust(name : String) : String {
    if (name.length > 30) {
        var nombre = name.substring(0..29) + "..."
        return nombre
    } else {
        return name
    }
}

fun typeAssignation(type : Boolean) : String{
    if (type) {
        return "Personal"
    } else {
        return "Grupal"
    }
}

fun durationCalc(dateI : String, dateF : String): Long {
    val formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val fechaI = LocalDate.parse(dateI, formatoFecha)
    val fechaF = LocalDate.parse(dateF, formatoFecha)

    val duration = ChronoUnit.DAYS.between(fechaI, fechaF) + 1

    return duration
}

fun TaskByDate(task : Tarea) {
    GlobalVariables.listOfTasks.add(task)

    val formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val initialDate = LocalDate.parse(task.dateI, formatoFecha)

    if (task.dateI != task.dateF) {
        val nextDay = initialDate.plusDays(1)
        var newTask = Tarea(task.type, task.name, formatoFecha.format(nextDay), task.dateF, task.timeI, task.timeF, task.duration)
        TaskByDate(newTask)
    }
}

fun Euclides(mes : Int) : Int {
    if (mes >= 0) {
        return mes
    } else {
        return mes + 12
    }
}
