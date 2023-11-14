package com.example.projecttodolist.Database

import android.util.Log
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.GlobalVariables.userdat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class homework(
    var idtarea: String? = null,
    var title: String? = null,
    var description: String? = null,
    var type: String? = null,
    var status: String? = null,
    var timefinished: String? = null, //ARRAY sera ahora para agregar grupos
    var grupos: String = userdat.grupos, //This is for groups
     )

fun  createtarea( username: String, password: String, email: String, idusername:String):Boolean { //Funcion registrar datos a REALTIME
    val dbRef = FirebaseDatabase.getInstance().getReference("tareas")
    var state: Boolean
    if (username.isEmpty() or password.isEmpty() or email.isEmpty()) {
        Log.e("empty data", "Los campos ingresados estan vacio")
        state = false
    }else{
        state = true
        val currentDate = LocalDate.now()
        // Define a custom date format
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        // Format the current date using the custom format
        val formattedDate = currentDate.format(formatter)

        //PARA CREAR FECHA DE CREACION ARRIBA

        GlobalVariables.userdat.userId = idusername
        GlobalVariables.userdat.password = password
        GlobalVariables.userdat.email = email
        GlobalVariables.userdat.createduser =formattedDate.toString()
        GlobalVariables.userdat.arraytodo = "No Groups"

        //UTILIZACION DE API JWTS
        val compactJws: String = Jwts.builder()
            .claim("UserID", GlobalVariables.userdat.userId.toString())
            .claim("username", GlobalVariables.userdat.username.toString())
            .claim("password", GlobalVariables.userdat.password.toString())
            .claim("email", GlobalVariables.userdat.email.toString())
            .claim("arraytodo", GlobalVariables.userdat.arraytodo.toString())
            .claim("createduser", GlobalVariables.userdat.createduser.toString())
            .claim("grupos", GlobalVariables.userdat.grupos.toString())
            .signWith(SignatureAlgorithm.HS256, "secret".toByteArray())
            .compact()

        GlobalVariables.userdat.token = compactJws

        dbRef.child(GlobalVariables.userdat.userId.toString()).setValue(GlobalVariables.userdat)
            .addOnCompleteListener {
                Log.e("Succesful adding", "Se ha ingresado con exito")

            }.addOnFailureListener {
                Log.e("Unsuccesful adding", "Se ha ingresado con exito")
            }


    }

    return state

}
