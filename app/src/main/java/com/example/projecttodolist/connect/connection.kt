package com.example.projecttodolist.connect

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.projecttodolist.UserData
import com.example.projecttodolist.tareadata
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.json.JSONArray
import org.json.JSONObject
import io.jsonwebtoken.Claims;

@Composable
fun loginrequest(user: String, password:String): String {
    var token = ""
    val urllink = "http://10.0.2.2:8085/datatodo/mostrardatos.php" //Utilizado SOLAMENTE CON EMULADOR
    val queue: RequestQueue = Volley.newRequestQueue(LocalContext.current) //En caso de emergencia el cambio fue de LocalContext.current
    var i = 0


    val request = StringRequest(
        Request.Method.GET,urllink,
        { response ->
            while (i < JSONArray(response).length()){
                val passwuard = JSONArray(response).getJSONObject(i).get("Password").toString()
                val usuar = JSONArray(response).getJSONObject(i).get("Username").toString()
                if (user == usuar &&   passwuard == password){
                    token = JSONArray(response).getJSONObject(i).get("Token").toString()
                }

                Log.e("Objects",passwuard + usuar)
            }



        },
        { Log.e("Objects","sin conex") })
    queue.add(request)

    return token
}
@Composable
fun showlist(tareaid: String): tareadata? { //muestra las listas de tarea
    val urllink = "http://10.0.2.2:8085/datatodo/mostrarlistas.php" //Utilizado SOLAMENTE CON EMULADOR
    val queue: RequestQueue = Volley.newRequestQueue(LocalContext.current) //En caso de emergencia el cambio fue de LocalContext.current
    var i = 0
    var tareas : tareadata? = null


    val request = StringRequest(
        Request.Method.GET,urllink,
        { response ->
            while (i < JSONArray(response).length()){

                val id = JSONArray(response).getJSONObject(i).get("Idtarea").toString()
                if (tareaid == id){

                    tareas = tareadata(JSONArray(response).getJSONObject(i).get("TimeCreated").toString()
                        ,JSONArray(response).getJSONObject(i).get("Title").toString()
                        ,JSONArray(response).getJSONObject(i).get("Description").toString()
                        ,JSONArray(response).getJSONObject(i).get("Type").toString()
                        ,JSONArray(response).getJSONObject(i).get("Status").toString().toInt(),
                        JSONArray(response).getJSONObject(i).get("TimeFinished").toString(),
                        JSONArray(response).getJSONObject(i).get("QrCode").toString())

                }

                Log.e("Objects", id)
            }



        },
        { Log.e("Objects","sin conexion para listas de tarea") })
    queue.add(request)

    return tareas
}


@Composable
fun codificar(user: String, password:String): String { //Función para poder codificar el token
    val compactJws: String = Jwts.builder()
        .claim("Username", user)
        .claim("Password", password)
        .claim("Email", password)
        .claim("DateCreated", password)
        .claim("Arraytodo", password)
        .claim("Age", password)
        .claim("Settings", password)
        .signWith(SignatureAlgorithm.HS256, "secret".toByteArray())
        .compact()

    return compactJws
}

@Composable
fun decodificar(user: String, password:String, compactJws:String, objetivo : String): String { //Función para poder codificar el token
    var data = ""
    try {
        // Parse and decode the JWT
        val claims: Claims = Jwts.parser()
            .setSigningKey("secret".toByteArray()) // Provide the same secret used for signing
            .parseClaimsJws(compactJws)
            .body

        // Access the claims
        data = claims[objetivo] as String
        //Objetivo significa a quien e.g Email, Age

        // Now you can use the email and password values
    } catch (e: Exception) {
        // Handle exceptions (e.g., token is invalid or has expired)
        e.printStackTrace()
    }
    return  data
}

@Composable //Agregue el composable
fun insertdata(userio : String, password: String, email:String, array : Array<String>, Age : Int, defaultset : Array<String>, token:String ){ //Utilizar para registrar sesion
    val urllink = "http://10.0.2.2:8085/datatodo/insertardatos.php" //Utilizado SOLAMENTE CON EMULADOR
    val queue: RequestQueue = Volley.newRequestQueue(LocalContext.current) //En caso de emergencia el cambio fue de LocalContext.current

    val usuario = UserData(
        userio, password,email, "2", array, Age, defaultset, token)
    val userDataJson = JSONObject(usuario.toString())


    val request = JsonObjectRequest(Request.Method.POST, urllink, userDataJson,
        { response ->
            Log.e("Response","JsonObjectRequest se realizo con exito")
            // Handle the successful response from the server
        },
        { error ->
            Log.e("Response","JsonObjectRequest failed XD")
            // Handle the error
        })
    queue.add(request)

}

@Composable //Agregue el composable
fun insertarea(TimeCreated : String, Title: String, Description:String, Type : String, Status : Int, TimeFinished : String, QrCode:String ){ //Utilizar para registrar sesion
    val urllink = "http://10.0.2.2:8085/datatodo/insertardatos.php" //Utilizado SOLAMENTE CON EMULADOR
    val queue: RequestQueue = Volley.newRequestQueue(LocalContext.current) //En caso de emergencia el cambio fue de LocalContext.current

    val usuario = tareadata(TimeCreated,Title,Description,Type,Status,TimeFinished,QrCode)
    val userDataJson = JSONObject(usuario.toString())


    val request = JsonObjectRequest(Request.Method.POST, urllink, userDataJson,
        { response ->
            Log.e("Response","JsonObjectRequest se realizo con exito")
            // Handle the successful response from the server
        },
        { error ->
            Log.e("Response","JsonObjectRequest failed XD")
            // Handle the error
        })
    queue.add(request)

}

@Composable //Agregue el composable
fun borrador() {
    val urllink = "http://10.0.2.2:8085/datatodo/mostrardatos.php" //Utilizado SOLAMENTE CON EMULADOR
    val queue: RequestQueue = Volley.newRequestQueue(LocalContext.current) //En caso de emergencia el cambio fue de LocalContext.current


    val request = StringRequest(
        Request.Method.GET,urllink,
        { response ->

            val data = JSONArray(response).toString()

            Log.e("Objects",data)


        },
        { Log.e("Objects","sin conex") })
    queue.add(request)
}

