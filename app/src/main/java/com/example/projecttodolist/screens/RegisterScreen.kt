package com.example.projecttodolist.screens

import androidx.compose.runtime.Composable
import com.example.projecttodolist.Database.save
import com.example.projecttodolist.GlobalVariables.userdat


//MI amigo composer

@Composable
fun RegisterScreen() { // EN EL MODELO USERMODEL ESTA LA FUNCION

    var usuario : String = ""
    var password : String = ""
    var email : String = ""
    //var settings : String = arrayOf(0,0,0,0,0,0).toString() //PRINCE AQUI PUEDES AGREGAR

    save(usuario, password, email) //Se puede agregar default settings como un button false true eg darkmode enable(1) or disabled(0)
    //ESTA FUNCION ES UTILIZADA PARA guardar elementos a la database PERO ES UN BOOL POR LO TANTO SI ES TRUE LLEGA AL LOGIN SCREEN SINO PUES SE QUEDA ACA





}