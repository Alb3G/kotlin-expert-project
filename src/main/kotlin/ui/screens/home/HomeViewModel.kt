package ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import model.Filter
import model.Note
import remote.NotesRepository
import remote.notesClient

class HomeViewModel(private val scope: CoroutineScope) {

    var state by mutableStateOf(UiState())
        private set

    init {
        loadNotes()
    }

    private fun loadNotes() {
        scope.launch {
            state = UiState(loading = true)
            val response = NotesRepository.getAll()
            state = UiState(notes = response)
        }
    }

    fun onFilterClick(filter: Filter) {
        state = state.copy(filter = filter)
    }

    data class UiState(
        var notes: List<Note>? = null,
        val loading: Boolean = false,
        val filter: Filter = Filter.All
    ) {
        val filteredNotes: List<Note>?
            get() = when(filter) {
            Filter.All -> notes
            is Filter.ByType -> notes?.filter { it.type == filter.type }
        }
    }
}