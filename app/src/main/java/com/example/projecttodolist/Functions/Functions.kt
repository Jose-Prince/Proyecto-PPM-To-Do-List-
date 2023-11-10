package com.example.projecttodolist.Functions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Tarea
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

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
    GlobalVariables.listWithAllDates.add(task)

    val formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val initialDate = LocalDate.parse(task.dateI, formatoFecha)

    if (task.dateI != task.dateF) {
        val nextDay = initialDate.plusDays(1)
        var newTask = Tarea(task.type, task.name, formatoFecha.format(nextDay), task.dateF, task.timeI, task.timeF, task.duration)
        TaskByDate(newTask)
    }
}

fun TaskByHour(task: Tarea){
    val formato = DateTimeFormatter.ofPattern("hh:mm a")
    val initialTime = LocalTime.parse(task.timeI, formato)
    val endTime = LocalTime.parse(task.timeF, formato)

    val formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val initialDate = LocalDate.parse(task.dateI, formatoFecha)

    if ((task.timeI != task.timeF) && (initialTime < endTime)) {
        val taskHour = Tarea(task.type, task.name,task.dateI, task.dateF, formato.format(initialTime), formato.format(initialTime.plusHours(1)),task.duration)
        GlobalVariables.listWithAllHours.add(taskHour)
        val nextHour = initialTime.plusHours(1)
        val newTask = Tarea(task.type,task.name,task.dateI, task.dateF,formato.format(nextHour),task.timeF,task.duration)
        TaskByHour(newTask)
    }
}

fun Euclides(mes : Int) : Int {
    if (mes >= 0) {
        return mes
    } else {
        return mes + 12
    }
}

fun organizeTaskInMap(listOfDates: ArrayList<Tarea>, map: MutableMap<String, ArrayList<Tarea>>) {
    map.clear()
    for (task in GlobalVariables.listOfTasks) {
        TaskByDate(task)
    }
    for (tarea in listOfDates){
        val taskDateI = tarea.dateI
        val taskList = map[taskDateI]

        if (taskList == null) {
            val newTaskList = arrayListOf(tarea)
            map[taskDateI] = newTaskList
        } else {
            taskList.add(tarea)
        }
    }
    listOfDates.clear()
}

fun organizeHoursInMap(listOfDates: ArrayList<Tarea>,map : MutableMap<String, Map<String, ArrayList<Tarea>>>){
    map.clear()
    for (task in GlobalVariables.listOfTasks) {
        TaskByDate(task)
    }
    for (tarea in listOfDates){
        TaskByHour(tarea)
    }
    val listOfTask = GlobalVariables.listWithAllHours

    for (tarea in listOfTask) {
        val dateI = tarea.dateI
        val timeI = tarea.timeI

        // Verificar si ya existe la fecha en el primer nivel del mapa
        val dateMap = (map[dateI] ?: mutableMapOf()).toMutableMap()

        // Verificar si ya existe la hora en el segundo nivel del mapa
        val timeList = dateMap[timeI] ?: arrayListOf()

        // Agregar la tarea a la lista
        timeList.add(tarea)

        // Actualizar el segundo nivel del mapa
        dateMap[timeI] = timeList

        // Actualizar el primer nivel del mapa
        map[dateI] = dateMap
    }
    GlobalVariables.listWithAllHours.clear()
    GlobalVariables.listWithAllDates.clear()
}

fun convertTo12HourFormat(time : String): String {
    val inputFormat = SimpleDateFormat("HH:mm", Locale.US)
    val outputFormat = SimpleDateFormat("hh:mm a", Locale.US)

    try {
        val date = inputFormat.parse(time)
        return outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return time
}








