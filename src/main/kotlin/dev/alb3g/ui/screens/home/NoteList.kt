package dev.alb3g.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.alb3g.model.Note

@Composable
fun NoteList(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(notes) { note ->
            Card(
                modifier = Modifier.padding(8.dp)
                .fillMaxWidth(0.8f)
                .clickable { onNoteClick(note) }
                .border(BorderStroke(2.dp, Color.Black)),
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