package com.github.mutantes.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mutantes.components.Body
import com.github.mutantes.components.Header
import com.github.mutantes.style.Colors

@Composable
fun Home() {
    Column(modifier = Modifier
        //.background(Colors.gray100) // cor pra debug
        .background(Colors.gray400)
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(118.dp)) {
        Header()
        Body()
    }
}