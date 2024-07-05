package dev.alb3g.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import dev.alb3g.model.Note
import dev.alb3g.model.Filter
import dev.alb3g.remote.NotesRepository

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