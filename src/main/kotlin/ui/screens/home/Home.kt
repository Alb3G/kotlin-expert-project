package ui.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier



@Composable
@Preview
fun Home(vm: HomeViewModel, onCreateClick: () -> Unit) {

    MaterialTheme {
        Scaffold(
            topBar = { TopBar(vm::onFilterClick) },
            floatingActionButton = { FloatingActionButton(onClick = onCreateClick){
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            } }
        )
        { padding ->
            Box(modifier = Modifier.fillMaxSize().padding(padding)) {
                if(vm.state.loading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                vm.state.filteredNotes?.let {
                    noteList(it)
                }
            }
        }
    }
}

