package com.example.projecttodolist.Database

import android.util.Log
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
    var grupos: String = "-$userId", //This is for groups
    var settings: String?= null, //
    var token: String?= null )


//Registrar

 lateinit var dbRef: DatabaseReference
    fun  save( username: String, password: String, email: String, idusername:String):Boolean { //Funcion registrar datos a REALTIME
        val dbRef = FirebaseDatabase.getInstance().getReference("usuario")
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

            userdat.userId = idusername
            userdat.password = password
            userdat.email = email
            userdat.createduser =formattedDate.toString()
            userdat.arraytodo = "No Groups"

            //UTILIZACION DE API JWTS
            val compactJws: String = Jwts.builder()
                .claim("UserID", userdat.userId.toString())
                .claim("username", userdat.username.toString())
                .claim("password", userdat.password.toString())
                .claim("email", userdat.email.toString())
                .claim("arraytodo", userdat.arraytodo.toString())
                .claim("createduser", userdat.createduser.toString())
                .claim("grupos", userdat.grupos)
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
     location: Int, idusuario:String //usuario principal no al que se elimina
){
    if (location == 0){ //FUNCION PARA ELIMINAR DATOS location significa en donde esta por ejemplo si esta en settings y elimina la cuenta en su localizacion es 0, por lo tanto eleimina la cuenta.
        val dbRef = FirebaseDatabase.getInstance().getReference("usuario").child(idusuario)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Log.e("DELETED USER", "Se ha eliminado la cuenta")

        }.addOnFailureListener{
            Log.e("USER STILL EXIST", "No se ha eliminado la cuenta")
        }
    }

}


fun deletegroupuser(
    idtarget: String, location: Int, idusuario:String //usuario principal no al que se elimina
){
        val dbRef = FirebaseDatabase.getInstance().getReference("usuario").child(idusuario)
        val grupo = userdat.grupos.replace("-$idtarget", "")
        userdat.grupos = grupo
        //userdat  = UserModel(userdat.userId, userdat.username, userdat.password, userdat.email, userdat.createduser,
            //userdat.arraytodo, userdat.grupos, userdat.settings, userdat.token)
        dbRef.setValue(userdat)
}

fun deletegrupo(
    idtarget: String, location: Int, idusuario:String //usuario principal no al que se elimina
){
    val dbRef = FirebaseDatabase.getInstance().getReference("usuario").child(idusuario)
    val array = userdat.grupos.split(",")
    val grupo = userdat.grupos.replace(array[location], "")

    userdat.grupos = grupo
    //userdat  = UserModel(userdat.userId, userdat.username, userdat.password, userdat.email, userdat.createduser,
    //userdat.arraytodo, userdat.grupos, userdat.settings, userdat.token)
    dbRef.setValue(userdat)
}


fun addgroup(
    idtarget: Int, idusuario:String //usuario principal no al que se elimina
){
    val dbRef = FirebaseDatabase.getInstance().getReference("usuario").child(idusuario)
    userdat.grupos = userdat.grupos + "-" + idtarget + "," //Entonces el - Es separado para los usuarios y la , sive para diviidir los grupos
    dbRef.setValue(userdat)
}


fun setvalue(
    valuetarget: String, location: String, idusuario:String //value target es el string que se desea cambiar y location
){
    val dbRef = FirebaseDatabase.getInstance().getReference("usuario").child(idusuario)
    //Username
    if (location == "username"){
        userdat.username = valuetarget
        dbRef.setValue(userdat)
    }
    if (location == "password"){
        userdat.password = valuetarget
        dbRef.setValue(userdat)
    }
    if (location == "email"){
        userdat.email = valuetarget
        dbRef.setValue(userdat)
    }
    if (location == "arraytodo"){ //Metes el ID de la tarea
        userdat.arraytodo = valuetarget
        dbRef.setValue(userdat)
    }

}

fun decodeJWT(token: String) {

    val secretKey = "secret" // Make sure it matches the key used for signing the token


    val claims = Jwts.parser()
        .setSigningKey(secretKey.toByteArray())
        .parseClaimsJws(token)
        .body

    // Access the claims

    // Access the claims
    userdat.userId = claims.get("UserID", String::class.java)
    userdat.username = claims.get("username", String::class.java)
    userdat.password = claims.get("password", String::class.java)
    userdat.email = claims.get("email", String::class.java)
    userdat.grupos = claims.get("grupos", String::class.java)
}

