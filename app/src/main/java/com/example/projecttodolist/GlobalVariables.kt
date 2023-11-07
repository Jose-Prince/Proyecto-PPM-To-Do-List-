package com.example.projecttodolist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.time.LocalDate
import java.util.Date

object GlobalVariables {
    var listOfTasks = ArrayList<Tarea>()
    var listWithAllDates = ArrayList<Tarea>()
    var listWithAllHours = ArrayList<Tarea>()
    var MapTaskDates : MutableMap<String, ArrayList<Tarea>> = mutableMapOf()
    var MapTaskHours : MutableMap<String, Map<String, ArrayList<Tarea>>> = mutableMapOf()
    var showTopBar by mutableStateOf(true)
    var showFloatingButton by mutableStateOf(true)
    var taskType by mutableStateOf(false)
    var year by mutableStateOf(LocalDate.now().year)
    var month by mutableStateOf(LocalDate.now().monthValue - 1)
    var destination by mutableStateOf(0)
}