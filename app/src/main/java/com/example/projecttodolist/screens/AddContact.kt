package com.example.projecttodolist.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.example.projecttodolist.R

//class AddContactActivity: ComponentActivity() {
//    private var textResult = mutableStateOf("")
//
//    private val barcodeLauncher = registerForActivityResult(ScanContract()) {
//            result ->
//        if (result.contents == null) {
//            Toast.makeText(this@AddContactActivity, "Cancelled", Toast.LENGTH_SHORT).show()
//        } else {
//            textResult.value = result.contents
//        }
//    }
//    private fun showCamera(){
//        val options = ScanOptions()
//        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
//        options.setPrompt("Analizando Código QR")
//        options.setCameraId(0)
//        options.setBeepEnabled(false)
//        options.setOrientationLocked(false)
//        barcodeLauncher.launch(options)
//    }
//
//    private val requestPermissionLauncher = registerForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) {
//            isGranted ->
//        if(isGranted) {
//            showCamera()
//        }
//    }
//
//    private fun checkCameraPermission(context: Context) {
//        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            showCamera()
//        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
//            Toast.makeText(this@AddContactActivity, "Se requiere acceder a la cámara del dispositivo", Toast.LENGTH_SHORT).show()
//        } else {
//            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
//        }
//    }
//}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContact(navController: NavController) {
    val navController = rememberNavController()
    val context = LocalContext.current

    var textResult = mutableStateOf("")

    val barcodeLauncher = rememberLauncherForActivityResult(ScanContract()) {
            result ->
        if (result.contents == null) {
            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            textResult.value = result.contents
        }
    }
    fun showCamera(){
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("Analizando Código QR")
        options.setCameraId(0)
        options.setBeepEnabled(false)
        options.setOrientationLocked(false)
        barcodeLauncher.launch(options)
    }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
            isGranted ->
        if(isGranted) {
            showCamera()
        }
    }

    fun checkCameraPermission(context: Context) {
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            showCamera()
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, android.Manifest.permission.CAMERA)) {
            Toast.makeText(context, "Se requiere acceder a la cámara del dispositivo", Toast.LENGTH_SHORT).show()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(text = "Add Request Screen Content")
        Spacer(modifier = Modifier.height(16.dp))
        FloatingActionButton(onClick = {
            checkCameraPermission(context)
        }) {
            Icon(painter = painterResource(
                id = R.drawable.qr_scan),
                modifier = Modifier.size(100.dp),
                contentDescription = "QR",
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Id usuario:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
//              Calling the API...
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Agregar")
        }
    }
}

@Preview
@Composable
fun AddContactScreenPrev() {
    val navController = rememberNavController()
    AddContact(navController)
}