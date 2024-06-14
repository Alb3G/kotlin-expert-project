package model

data class Note(val title: String, val description: String, val type: Type) {
    enum class Type { TEXT, SOUND }

}

fun getNotes(): List<Note> {
    val list = (1..10).map {
        Note(
            "Title $it" ,
            "Description $it",
            if(it % 2 == 0) Note.Type.SOUND else Note.Type.TEXT
        )
    }
    return list
}