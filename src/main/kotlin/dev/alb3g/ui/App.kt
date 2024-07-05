package dev.alb3g.ui

import androidx.compose.runtime.*
import dev.alb3g.ui.screens.detail.Detail
import dev.alb3g.ui.screens.detail.DetailViewModel
import dev.alb3g.ui.screens.home.Home;
import dev.alb3g.ui.screens.home.HomeViewModel

sealed interface Route {
    data object Home: Route
    data class Detail(val id: Long): Route
}

@Composable
fun App() {
    var route by remember { mutableStateOf<Route>(Route.Home) }
    val scope = rememberCoroutineScope()
    route.let {
        when(it) {
            Route.Home -> Home(
                vm = HomeViewModel(scope),
                onNoteClick = { noteId -> route = Route.Detail(noteId) }
            )
            is Route.Detail -> Detail(vm = DetailViewModel(scope, it.id), onClose = { route = Route.Home })
        }
    }
}
