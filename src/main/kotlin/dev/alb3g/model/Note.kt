package dev.alb3g.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(val title: String, val description: String, val type: Type, val id: Long = NEW_NOTE,) {
    enum class Type { TEXT, SOUND }
    companion object {
        const val NEW_NOTE = -1L
    }
}