package dev.alb3g.extensions

import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.update(update: T.() -> T) {
    value = update(value);
}