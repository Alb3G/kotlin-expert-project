import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.Note

@Composable
@Preview
fun App(appState: AppState): Unit = with(appState) {
    val notes = state.value.notes;

    if (notes == null) {
        LaunchedEffect(true) {
            loadNotes();
        }
    }

    MaterialTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if(state.value.loading) {
                CircularProgressIndicator()
            }

            notes?.let {
                noteList(it)
            }
        }
    }
}

@Composable
private fun noteList(notes: List<Note>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notes) {
                note -> Card(
            modifier = Modifier.padding(8.dp).fillMaxWidth(0.8f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    Text(
                        text = note.title,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.weight(1f)
                    )
                    if(note.type == Note.Type.SOUND) {
                        Icon(imageVector = Icons.Default.Mic, contentDescription = "");
                    } else {
                        Icon(imageVector = Icons.Default.Description, contentDescription = "");
                    }
                }
                Spacer(modifier = Modifier.height(8.dp));
                Text(text = note.description);
            }
        }
        }
    }
}

fun main() {
    val appState = AppState;
    application {
        Window(onCloseRequest = ::exitApplication) {
            App(appState);
        }
    }
}
