package com.example.projecttodolist

import java.time.LocalDate


class Task  {
    var id : String = ""
    var name : String = ""
    var date = LocalDate.now()
    var time : String = ""

}

object GlobalVariables {
    lateinit var listOfTasks : MutableList<Task>
}