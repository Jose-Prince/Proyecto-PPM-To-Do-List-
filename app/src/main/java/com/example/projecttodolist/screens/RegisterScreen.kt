package com.example.projecttodolist.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Database.save
import com.example.projecttodolist.Database.signUpUser
import com.example.projecttodolist.Functions.DrawShape
import com.example.projecttodolist.GlobalVariables.userdat
import com.example.projecttodolist.Navigation.AppScreens
import com.example.projecttodolist.ui.theme.blue
import com.example.projecttodolist.ui.theme.gray
import com.example.projecttodolist.ui.theme.green
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

    var user : String = ""
    var password : String = ""
    val context = LocalContext.current
    var email : String = ""
    //var settings : String = arrayOf(0,0,0,0,0,0).toString() //PRINCE AQUI PUEDES AGREGAR

    //save(user, password, email) //Se puede agregar default settings como un button false true eg darkmode enable(1) or disabled(0)
    //ESTA FUNCION ES UTILIZADA PARA guardar elementos a la database PERO ES UN BOOL POR LO TANTO SI ES TRUE LLEGA AL LOGIN SCREEN SINO PUES SE QUEDA ACA

    var usuario by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var maxCharacters : Int = 36
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(gray)
    ){

    }
    Column (modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter),
        verticalArrangement = Arrangement.Center){
        DrawShape(shape = RectangleShape)
        Spacer(modifier = Modifier.height(125.dp))

        Text(
            text = "Usuario:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Row {
            Spacer(modifier = Modifier.width(55.dp))
            OutlinedTextField(
                value = usuario,
                onValueChange = {newText ->
                    if (newText.length <= maxCharacters)
                        usuario = newText
                        userdat.username = usuario
                },
                modifier = Modifier
                    .border(width = 2.dp, blue, RoundedCornerShape(32.dp))
                    .background(blue, shape = RoundedCornerShape(32.dp)),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                textStyle = TextStyle(textAlign = TextAlign.Center,
                    fontSize = 21.sp)
            )

        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Correo:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Row {
            Spacer(modifier = Modifier.width(55.dp))
            OutlinedTextField(
                value = correo,
                onValueChange = {newText ->
                    if (newText.length <= maxCharacters)
                        correo = newText
                    userdat.email = correo
                },
                modifier = Modifier
                    .border(width = 2.dp, blue, RoundedCornerShape(32.dp))
                    .background(blue, shape = RoundedCornerShape(32.dp)),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                textStyle = TextStyle(textAlign = TextAlign.Center,
                    fontSize = 21.sp)
            )

        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Contraseña:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Spacer(modifier = Modifier.width(55.dp))
            OutlinedTextField(
                value = contraseña,
                onValueChange = {newText ->
                    if (newText.length <= maxCharacters)
                        contraseña = newText
                },
                modifier = Modifier
                    .border(width = 2.dp, blue, RoundedCornerShape(32.dp))
                    .background(blue, shape = RoundedCornerShape(32.dp)),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Confirmar contraseña:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Spacer(modifier = Modifier.width(55.dp))
            OutlinedTextField(
                value = confirm,
                onValueChange = {newText ->
                    if (newText.length <= maxCharacters) {
                        confirm = newText
                        userdat.password = confirm
                    }

                },
                modifier = Modifier
                    .border(width = 2.dp, blue, RoundedCornerShape(32.dp))
                    .background(blue, shape = RoundedCornerShape(32.dp)),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)){
            Spacer(modifier = Modifier.width(117.dp))
            Button(
                onClick = {
                    val auth = FirebaseAuth.getInstance()
                    if(signUpUser(auth, userdat.email.toString(), contraseña, userdat.password.toString(), userdat.username.toString())){
                        navController.navigate(route = AppScreens.Bar.route)
                    }
                    else{
                        Toast.makeText(context, "Cargando", Toast.LENGTH_SHORT).show()
                    }
                     }, //Puede ser esto
                colors = ButtonDefaults.buttonColors(green)
            ) {
                Text(text = "Resgistrarse",
                    fontSize = 21.sp)
            }
        }
    }
}

@Preview
@Composable
fun RegisterPrev(){
    val navController = rememberNavController()
    RegisterScreen(navController = navController)
}