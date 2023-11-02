package com.example.projecttodolist.dataStorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.projecttodolist.Tarea
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first

class StoreUserTask(private val context: Context) {

    // to make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserTask")
        val USER_TASK_KEY = stringPreferencesKey("user_task")
    }

    // save the task array
    suspend fun saveTasks(tasks: ArrayList<Tarea>) {
        val json = Gson().toJson(tasks)
        context.dataStore.edit { preferences ->
            preferences[USER_TASK_KEY] = json
        }
    }

    suspend fun loadTasks(): ArrayList<Tarea> {
        val json = context.dataStore.data.first()[USER_TASK_KEY]
        return if (!json.isNullOrBlank()) {
            Gson().fromJson(json, object : TypeToken<ArrayList<Tarea>>() {}.type)
        } else {
            ArrayList()
        }
    }
}