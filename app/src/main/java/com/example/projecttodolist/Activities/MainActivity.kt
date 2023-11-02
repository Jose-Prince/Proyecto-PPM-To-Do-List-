package com.example.projecttodolist.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Navigation.AppNavigation
import com.example.projecttodolist.dataStorage.StoreUserTask
import com.example.projecttodolist.ui.theme.ProjectToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectToDoListTheme {
                // A surface container using the 'background' color from the theme
                ProjectToDoListTheme(
                    darkTheme = false
                ) {
                    val context = LocalContext.current
                    val dataStore = StoreUserTask(context)

                    LaunchedEffect(Unit) {
                        val loadedTasks = dataStore.loadTasks()
                        //taskViewModel.refresh()
                        GlobalVariables.listOfTasks = loadedTasks
                    }
                    AppNavigation()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
        // A surface container using the 'background' color from the theme
    ProjectToDoListTheme(
            darkTheme = false
        ) {
            AppNavigation()
        }
}
