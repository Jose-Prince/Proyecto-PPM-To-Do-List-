package com.example.projecttodolist.Database

import android.util.Log
import android.widget.Toast
import com.example.projecttodolist.GlobalVariables.userdat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.LocalDate
import java.time.format.DateTimeFormatter


/*
BASICAMENTE ESTA CLASE ES UTILIZADA PARA ELIMINAR PERSONAS DEL GRUPO, CREAR AL USUARIO PARA SABER LAS LISTAS DE TRABAJO
 */

data class UserModel(
    var userId: String? = null,
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var createduser: String? = null,
    var arraytodo: String? = null, //ARRAY sera ahora para agregar grupos
    var settings: String?= null,
    var token: String?= null )


//Registrar

 lateinit var dbRef: DatabaseReference
    fun  save( username: String?, password: String?, email: String?, idusername:String):Boolean { //Funcion registrar datos a REALTIME
        val dbRef = FirebaseDatabase.getInstance().getReference("usuario")
        var state: Boolean
        if (username.toString().isEmpty() or password.toString().isEmpty() or email.toString().isEmpty()) {
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

            userdat.userId = idusername
            userdat.password = password.toString()
            userdat.email = email.toString()
            userdat.createduser =formattedDate.toString()
            userdat.arraytodo = "No Groups"

            //UTILIZACION DE API JWTS
            val compactJws: String = Jwts.builder()
                .claim("UserID", userdat.userId.toString())
                .claim("username", userdat.username.toString())
                .claim("password", userdat.password.toString())
                .claim("email", userdat.email)
                .claim("grupos", userdat.arraytodo)
                .claim("createduser", userdat.createduser)
                .signWith(SignatureAlgorithm.HS256, "secret".toByteArray())
                .compact()

            userdat.token = compactJws

            dbRef.child(userdat.userId.toString()).setValue(userdat)
                .addOnCompleteListener {
                    Log.e("Succesful adding", "Se ha ingresado con exito")

                }.addOnFailureListener {
                    Log.e("Unsuccesful adding", "Se ha ingresado con exito")
                }


        }

        return state

    }


fun deleteRecord(
    idtarget: String, location: Int, idusuario:String //usuario principal no al que se elimina
){
    if (location == 0){ //FUNCION PARA ELIMINAR DATOS location significa en donde esta por ejemplo si esta en settings y elimina la cuente su localizacion es 0, por lo tanto eleimina la cuenta.
        val dbRef = FirebaseDatabase.getInstance().getReference("usuario").child(idusuario)
        val mTask = dbRef.removeValue()
        eliminarusuarioauth(idusuario) //de fireauthentication

        mTask.addOnSuccessListener {
            Log.e("DELETED USER", "Se ha eliminado la cuenta")

        }.addOnFailureListener{
            Log.e("USER STILL EXIST", "No se ha eliminado la cuenta")
        }
    }
    if(location == 1){
            val dbRef = FirebaseDatabase.getInstance().getReference("usuario").child(idusuario)
            val settings = userdat.settings.toString().replace(idtarget, "")
            userdat.settings = settings
            val userinfo  = userdat
            dbRef.setValue(userinfo)

    }
}

