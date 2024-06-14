import androidx.compose.runtime.mutableStateOf
import model.getNotes

object AppState {
    val notes = mutableStateOf(getNotes());
}