package com.example.projecttodolist.screens

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import com.example.projecttodolist.R

class AddContactActivity: ComponentActivity() {
    private var textResult = mutableStateOf("")

    private val barcodeLauncher = registerForActivityResult(ScanContract()) {
            result ->
        if (result.contents == null) {
            Toast.makeText(this@AddContactActivity, "Cancelled", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this@AddContactActivity, "Se requiere acceder a la cámara del dispositivo", Toast.LENGTH_SHORT).show()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContact(navController: NavController) {
    val navController = rememberNavController()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(text = "Add Request Screen Content")
        Spacer(modifier = Modifier.height(16.dp))
        FloatingActionButton(onClick = ()) {
            Icon(painter = painterResource(
                id = R.drawable.qr_scan),
                modifier = Modifier.size(100.dp),
                contentDescription = "QR",
            )
        }

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