package model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

data class Note(val id: Int, val title: String, val description: String, val type: Type) {
    enum class Type { TEXT, SOUND }

}

fun getNotes(): Flow<List<Note>> = flow {
    var notes = emptyList<Note>();
    delay(2000);
    (1..10).forEach {
        notes = notes + Note(
            it,
            "Title $it" ,
            "Description $it",
            if(it % 2 == 0) Note.Type.SOUND else Note.Type.TEXT
        )
        emit(notes);
        delay(500);
    }

}
