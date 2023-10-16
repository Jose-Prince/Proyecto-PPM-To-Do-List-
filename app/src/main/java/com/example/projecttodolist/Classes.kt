package com.example.projecttodolist

data class Tarea(
    var type : String,
    var name : String,
    var dateI : String,
    var dateF : String,
    var timeI : String,
    var timeF : String,
    var duration : Long
)
