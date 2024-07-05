package dev.alb3g.model

sealed interface Filter {
    data object All: Filter
    class ByType(val type: Note.Type): Filter
}