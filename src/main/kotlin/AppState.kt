import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import model.Note
import model.getNotes

object AppState {
    var state by mutableStateOf(UiState());

    suspend fun loadNotes(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            state = UiState(loading = true);
            state = UiState(notes = getNotes())
        }
    }

    data class UiState(
        var notes: List<Note>? = null,
        val loading: Boolean = false
    )
}