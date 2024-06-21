package model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

data class Note(val id: Int, val title: String, val description: String, val type: Type) {
    enum class Type { TEXT, SOUND }

}

suspend fun getNotes() = withContext(Dispatchers.IO) {
    delay(2000);
    val list = (1..10).map {
        Note(
            it,
            "Title $it" ,
            "Description $it",
            if(it % 2 == 0) Note.Type.SOUND else Note.Type.TEXT
        )
    }
    list
}
