package model

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

data class Note(val id: Int, val title: String, val description: String, val type: Type) {
    enum class Type { TEXT, SOUND }
    companion object
}

val Note.Companion.fakeNotes get() = flow {
    delay(2000);
    val notes = (1..10).map {
        Note(
            it,
            "Title $it" ,
            "Description $it",
            if(it % 2 == 0) Note.Type.SOUND else Note.Type.TEXT
        )
    }
    emit(notes)
}
