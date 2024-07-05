package dev.alb3g.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.alb3g.model.Note

@Composable
fun Detail(vm: DetailViewModel, onClose: () -> Unit) {
    val note = vm.state.note
    Scaffold(
        topBar = {
            TopBar(
                note = note,
                onClose = onClose,
                onSave = vm::save,
                onDelete = vm::delete
            )
        }
    ) {
        if(vm.state.saved) {
            onClose()
        }
        if(vm.state.loading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                OutlinedTextField(
                    value = note.title,
                    onValueChange = { vm.update(note.copy(title = it)) },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                    label = { Text("Title") },
                    maxLines = 1
                )
                TypeDropDown(value = note.type, onValueChange = { vm.update(note.copy(type = it)) }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(
                    value = note.description,
                    onValueChange = { vm.update(note.copy(description = it)) },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }
        }
    }
}

@Composable
private fun TopBar(note: Note, onClose: () -> Unit, onSave: () -> Unit, onDelete: () -> Unit) {
    TopAppBar(
        title = { Text(note.title) },
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close Detail")
            }
        },
        actions = {
            IconButton(onClick = onSave) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
            }
            if(note.id != Note.NEW_NOTE) {
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")
                }
            }
        }
    )
}

@Composable
private fun TypeDropDown(value: Note.Type, onValueChange: (Note.Type) -> Unit, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf( false ) }

    Box(
        modifier = modifier,
        propagateMinConstraints = true
    ) {
        OutlinedTextField(
            value = value.toString(),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = {
                    expanded = true
                }) { Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Display Note value") }
            }
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false} ) {
            Note.Type.entries.forEach {
                DropdownMenuItem(onClick = { onValueChange(it); expanded = false }) { Text(it.name) }
            }
        }
    }
}