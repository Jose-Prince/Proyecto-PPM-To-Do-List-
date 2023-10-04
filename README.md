# Proyecto To-Do List

## API To-Do List
API para la gestión de tareas a travez de To-Do List backend 

### Get Tasks

**Endpoint** ```/v1/tasks/:status/```  

**Method** GET  

**Path variables**  
|Variable|Value|
|---|---|
|status|pending, done, cancelled|

**Description**
Obtiene la lista de tareas vinculadas a un usuario.

**Headers**
```
{
    contentType: "application/json",
    Authorization: "Bearer {{auth_token}}"
}
```

**Schema Response**
```
{
    sucess: true,
    response: [
        {
            id: 1,
            title: Example task number 1,
            status: pending
        },
        {
            id: 2,
            title: Example task number 2,
            status: pending
        },
        {
            id: 3,
            title: Example task number 3,
            status: cancelled
        },
    ]
}
```

### Get Tasks

**Endpoint** ```/v1/security/authentication/login/```  
**Method** POST  

**Description**
Método de autenticación de usuarios para habilitar el uso los recursos privados del API, verifica las credenciales del usuario mediante el tipo de autenticación y retorna un auth_token con expiración de 1 hora.  

**Headers**
```
{
    contentType: "application/json",
}
```

**Schema Request**
```
{
    authenticationType: "EmailAndPassword", // oAuth, SSO, EmailAndPassword, API key
    username: "MyUserName",
    password: "MyPassword"
}
```

**Schema Response**
```
{
    sucess: true,
    response: {
        authToken: {{token_hash}} 
    }
}
```

## API QRGen
- Agregar en Dependencias 
    ```ruby
     implementation 'com.github.kenglxn.QRGen:javase:3.0.1'
     ```

     [Ejemplo programado](https://github.com/kenglxn/QRGen#usage).


## Notificaciones de Eventos

- 

### **Librerías**


## Volley
 # Usage
- Conexión de API de manera más eficiente en vez de Retrofit
    - Utilización de Volley para llamar a otras APIs
    -  Volley es una biblioteca de Android que simplifica la realización de solicitudes HTTP, ofrece una gestión eficiente de hilos,        priorización de solicitudes, manejo de errores y soporte para almacenamiento en caché y carga de imágenes. Su facilidad de uso y amplia adopción hacen que sea una elección sólida para realizar llamadas a API en aplicaciones Android.

# Pasos 

1. Agregar Dependencias
    ```ruby
     implementation 'com.android.volley:volley:1.1.1'

     //Instanciar la variable request de Volley
     val queue = Volley.newRequestQueue(this)
     ```


## Android X Core Library
 # Usage
- Brindar Notificaciones a usuarios cuando hay eventos proximos o si hay un cambio de planificación e incluso si alguien a agregado contenido.

# Pasos 

1. Agregar Dependencias
    ```ruby
     implementation("androidx.core:core-ktx:2.2.0")
     ```

     [Ejemplos de programacion][https://developer.android.com/develop/ui/views/notifications/build-notification] 

