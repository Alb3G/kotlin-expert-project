import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Note

class ViewModel {
    private val _state: MutableStateFlow<Note> = MutableStateFlow(Note(1,"Title 1","Description 1",Note.Type.TEXT))
    val state: StateFlow<Note> = _state.asStateFlow()
    suspend fun update() {
        var count = 1;
        var noteType: Note.Type;
        while(true) {
            delay(2000);
            count++;
            noteType = if(count % 2 == 0) Note.Type.SOUND else Note.Type.TEXT;
            _state.value = Note(count, "Title $count", "Description $count", noteType);
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