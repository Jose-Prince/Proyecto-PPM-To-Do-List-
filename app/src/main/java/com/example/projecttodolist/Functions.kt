package com.example.projecttodolist

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import kotlin.random.Random

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
    if (name.length > 30) {
        var nombre = name.substring(0..29) + "..."
        return nombre
    } else {
        return name
    }
}

fun tyoeAssignation(type : Boolean) : String{
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
        var newTask = Tarea(task.type, task.name, formatoFecha.format(nextDay), task.dateF, task.time, task.duration)
        TaskByDate(newTask)
    }
}
