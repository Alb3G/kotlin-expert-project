package dev.alb3g.ui.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.*
import dev.alb3g.model.Note
import dev.alb3g.model.Filter

@Composable
fun TopBar(onFilterClick: (Filter) -> Unit) {
    TopAppBar(
        title = { Text("My Notes") },
        actions = {
            FilterActions(onFilterClick)
        }
    )
}

@Composable
private fun FilterActions(onFilterClick: (Filter) -> Unit) {
    val pairList: List<Pair<Filter, String>> = listOf(
        Filter.All to "All",
        Filter.ByType(Note.Type.TEXT) to "Text",
        Filter.ByType(Note.Type.SOUND) to "Sound"
    )
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = {
        expanded = true;
    }) {
        Icon(
            imageVector = Icons.Default.FilterList,
            contentDescription = "Filter Notes"
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            pairList.forEach { (filter, text) ->
                DropdownMenuItem(onClick = {
                    expanded = false;
                    onFilterClick(filter)
                }) {
                    Text(text)
                }
            }
        }
    }
}