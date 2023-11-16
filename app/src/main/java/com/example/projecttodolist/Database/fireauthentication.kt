package com.example.projecttodolist.Database

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import com.example.projecttodolist.GlobalVariables.userdat
import com.example.projecttodolist.screens.MainTaskScreen
import com.google.firebase.auth.FirebaseAuth

/*
FUNCIONES PARA PODER AUTHENTICARSE, EN BASE A AESTO SE PIDE QUE REGRESE EL ID DEL AUTENTICADOR Y COMPARAR CON USERMODEL.KT
 */

fun signUpUser(auth: FirebaseAuth, email: String, password : String, confirmation : String, context : Context): Boolean {
    var statementsign = true
    if (email.isBlank() || password.isBlank() || confirmation.isBlank()) {
        Toast.makeText(context, "Correo y contraseña vacios", Toast.LENGTH_SHORT).show()
        statementsign = false
        return statementsign
    }

    if (password != confirmation) {
        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        statementsign = false
        return statementsign
    }
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Usuario Registrado", Toast.LENGTH_SHORT).show()
                userdat.userId
            } else {
                Toast.makeText(context, "Registro fallido", Toast.LENGTH_SHORT).show()
            }
        }

    return statementsign

}





fun login(auth: FirebaseAuth, email : String, password: String, context: Context):Boolean {
    var statement = false
    if  (email.isBlank() || password.isBlank()) {
        Toast.makeText(context, "Correo o contraseña vacios", Toast.LENGTH_SHORT).show()
        return false

    }
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { signInTask ->
            if (signInTask.isSuccessful) {
                Toast.makeText(context, "Sesion iniciada", Toast.LENGTH_SHORT).show()
                val currentUser = auth.currentUser?.uid.toString()
                userdat.userId = currentUser
                statement = true

            } else {
                auth.fetchSignInMethodsForEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            val result = task.result
                            if (result.equals(email)){
                                Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            if (isInternetAvailable(context)) {
                                Toast.makeText(context, "Correo inválido", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Sin Internet", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }
                statement = false
            }
        }
    return statement
}

fun isInternetAvailable(context: Context) : Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork
    val capabilities = connectivityManager.getNetworkCapabilities(network)
    return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}



