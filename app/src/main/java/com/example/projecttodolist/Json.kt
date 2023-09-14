import android.util.JsonWriter
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter


@Throws(IOException::class)
fun writeJsonStream(out: OutputStream?, messages: List<Todo?>) {
    val writer = JsonWriter(OutputStreamWriter(out, "UTF-8"))
    writer.setIndent("  ")
    writeMessagesArray(writer, messages)
    writer.close()
}

@Throws(IOException::class)
fun writeMessagesArray(writer: JsonWriter, messages: List<Message?>) {
    writer.beginArray()
    for (message in messages) {
        writeMessage(writer, message)
    }
    writer.endArray()
}

@Throws(IOException::class)
fun writeMessage(writer: JsonWriter, message: Message) {
    writer.beginObject()
    writer.name("id").value(message.getId())
    writer.name("text").value(message.getText())
    if (message.getGeo() != null) {
        writer.name("geo")
        writeDoublesArray(writer, message.getGeo())
    } else {
        writer.name("geo").nullValue()
    }
    writer.name("user")
    writeUser(writer, message.getUser())
    writer.endObject()
}

@Throws(IOException::class)
fun writeUser(writer: JsonWriter, user: User) {
    writer.beginObject()
    writer.name("name").value(user.getName())
    writer.name("followers_count").value(user.getFollowersCount())
    writer.endObject()
}

@Throws(IOException::class)
fun writeDoublesArray(writer: JsonWriter, doubles: List<Double?>) {
    writer.beginArray()
    for (value in doubles) {
        writer.value(value)
    }
    writer.endArray()
}