import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import model.Note
import model.getNotes

object AppState {
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    suspend fun loadNotes(coroutineScope: CoroutineScope) {

        coroutineScope.launch {
            _state.value = UiState(loading = true);
            getNotes().collect {
                _state.value = UiState(notes = it)
            }
        }
    }

    data class UiState(
        var notes: List<Note>? = null,
        val loading: Boolean = false
    )
}