package model

data class Note(val id: Int, val title: String, val description: String, val type: Type) {
    enum class Type { TEXT, SOUND }

}

fun getNotes(callBack: (List<Note>) -> Unit) {
    Thread.sleep(2000);
    val list = (1..10).map {
        Note(
            it,
            "Title $it" ,
            "Description $it",
            if(it % 2 == 0) Note.Type.SOUND else Note.Type.TEXT
        )
    }
    callBack(list)
}
