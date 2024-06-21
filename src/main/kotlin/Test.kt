import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Note

class ViewModel {
    private val _state: MutableStateFlow<Note> = MutableStateFlow(Note(1, "Title 1", "Description 1", Note.Type.TEXT))
    val state = _state.asStateFlow()
    suspend fun update() {
        var count = 1;
        var noteType: Note.Type;
        while(true) {
            delay(500);
            noteType = if(count % 2 == 0) Note.Type.SOUND else Note.Type.TEXT;
            _state.value = Note(count, "Title $count", "Description $count", noteType);
            count++;
        }
    }
}

fun main(): Unit = runBlocking {
    val viewModel = ViewModel()
    launch {
        viewModel.update()
    }
    viewModel.state.collect(::println)
}