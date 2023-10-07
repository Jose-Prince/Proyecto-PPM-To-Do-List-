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
        _taskState.update { GlobalVariables.listOfTasks }
    }

    fun refresh() {
        _taskState.update { GlobalVariables.listOfTasks }
    }

    fun removeItem(currentItem: Tarea) {
        _taskState.update {
            val mutableList = it.toMutableList()
            mutableList.remove(currentItem)
            GlobalVariables.listOfTasks.remove(currentItem)
            mutableList
        }
    }
}