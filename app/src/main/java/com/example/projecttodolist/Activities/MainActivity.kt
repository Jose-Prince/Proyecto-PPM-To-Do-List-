package com.example.projecttodolist.Activities

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.projecttodolist.Functions.TaskByDate
import com.example.projecttodolist.Functions.organizeTaskInMap
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Navigation.AppNavigation
import com.example.projecttodolist.dataStorage.StoreUserTask
import com.example.projecttodolist.ui.theme.ProjectToDoListTheme
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : ComponentActivity() {

    private var textResult = mutableStateOf("")

    private val barcodeLauncher = registerForActivityResult(ScanContract()) {
            result ->
        if (result.contents == null) {
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            textResult.value = result.contents
        }
    }
    private fun showCamera(){
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Analizando Código QR")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setOrientationLocked(false)
        barcodeLauncher.launch(options)
    }

    private val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
        isGranted ->
        if(isGranted) {
            showCamera()
        }
    }

    private fun checkCameraPermissioncheckCameraPermission(context: Context) {
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            showCamera()
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            Toast.makeText(this@MainActivity, "Se requiere acceder a la cámara del dispositivo", Toast.LENGTH_SHORT).show()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

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
                        GlobalVariables.listOfTasks = loadedTasks
                        for (task in GlobalVariables.listOfTasks){
                            TaskByDate(task)
                        }
                        organizeTaskInMap(GlobalVariables.listWithAllDates, GlobalVariables.MapTaskDates)
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
