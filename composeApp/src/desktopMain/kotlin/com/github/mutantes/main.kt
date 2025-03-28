package com.github.mutantes

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {

    val state = rememberWindowState(
        height = 800.dp,
        width = 450.dp
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "currency-converter",
        state = state,
        resizable = true
    ) {
        App()
    }
}