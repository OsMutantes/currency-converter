package com.github.mutantes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mutantes.style.Colors
import habittracker.composeapp.generated.resources.Res
import habittracker.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(painter = painterResource(Res.drawable.logo), contentDescription = null)
    }
}