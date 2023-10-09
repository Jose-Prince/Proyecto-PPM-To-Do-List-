package com.example.projecttodolist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {
    private val _taskState = MutableStateFlow(emptyList<Tarea>())
    val taskState : StateFlow<List<Tarea>> = _taskState.asStateFlow()

    init {
        _taskState.update { allTasks() }
    }

    fun refresh() {
        _taskState.update { allTasks() }
    }

    fun removeItem(currentItem: Tarea) {
        _taskState.update {
            val mutableList = it.toMutableList()
            mutableList.remove(currentItem)
            mutableList
        }
        updateGlobalTasks(_taskState.value)
    }
    private fun allTasks() = GlobalVariables.listOfTasks

    private fun updateGlobalTasks(tasks: List<Tarea>){
        GlobalVariables.listOfTasks = tasks.toMutableList() as ArrayList<Tarea>
    }
}