package com.example.projecttodolist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.projecttodolist.Database.UserModel
import com.example.projecttodolist.Database.homework
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
    var userdat = UserModel(userId = null, username = null,password = null,email = null,createduser = null,arraytodo = null,settings = null, token = null)
    var tareas = homework(idtarea = null, title = null, description = null, type = null, status = null, timefinished = null)
    val dbRefusuaio = FirebaseDatabase.getInstance().getReference("usuario")
    val dbtareas = FirebaseDatabase.getInstance().getReference("tareas")
    val auth = FirebaseAuth.getInstance()
    var loginbool = false

}