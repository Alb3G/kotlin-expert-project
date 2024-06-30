package ui

import androidx.compose.runtime.*
import ui.screens.detail.Detail
import ui.screens.home.Home;

sealed interface Route {
    data object Home: Route
    data class Detail(val id: Long): Route
}

@Composable
fun App() {
    var route by remember { mutableStateOf<Route>(Route.Home) }
    route.let {
        when(it) {
            Route.Home -> Home(onCreateClick = {route = Route.Detail(-1)})
            is Route.Detail -> Detail(it.id)
        }
    }
}
