import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.alb3g.ui.App

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication) {
            App();
        }
    }
}
