import androidx.compose.runtime.mutableStateOf
import extensions.update
import model.Note
import model.getNotes
import kotlin.concurrent.thread

object AppState {
    val state = mutableStateOf(UiState());

    fun loadNotes() {
        thread {
            state.update { copy(loading = true) }
            getNotes { notes -> state.update { UiState(notes = notes, loading = false) } }
        }
    }

    data class UiState(
        var notes: List<Note>? = null,
        val loading: Boolean = false
    )
}