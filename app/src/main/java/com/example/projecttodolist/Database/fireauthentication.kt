package com.example.projecttodolist.Database

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import com.example.projecttodolist.GlobalVariables.auth
import com.example.projecttodolist.GlobalVariables.loginbool
import com.example.projecttodolist.GlobalVariables.userdat
import com.example.projecttodolist.screens.MainTaskScreen
import com.google.firebase.auth.FirebaseAuth

/*
FUNCIONES PARA PODER AUTHENTICARSE, EN BASE A AESTO SE PIDE QUE REGRESE EL ID DEL AUTENTICADOR Y COMPARAR CON USERMODEL.KT
 */

fun signUpUser(auth: FirebaseAuth, email: String, password : String, confirmation : String, username : String): Boolean {
    var statementsign = false
    if (email.isBlank() || password.isBlank() || confirmation.isBlank() || username.isBlank()) {
       Log.e("no credetentials", "Sin credencialess")
        statementsign = false
        return statementsign
    }

    if (password != confirmation) {
        Log.e("Contrasñas no iguales", "Las contraseñas no son iguales")
        statementsign = false
        return statementsign
    }
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                Log.e("register succesful", "Se registro exitosamente")
                val user = auth.currentUser
                if (user != null) {
                    val uid = user.uid
                    userdat.userId = uid
                    save(
                        userdat.username.toString(),
                        userdat.password.toString(),
                        userdat.email.toString(),
                        userdat.userId.toString()
                    )
                    statementsign = true
                }

            } else {
                Log.e("no Register ", "Register Failed")
                statementsign = false
            }
        }

    return statementsign

}







