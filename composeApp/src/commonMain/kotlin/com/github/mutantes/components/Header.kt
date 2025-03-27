package com.github.mutantes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import currency_converter.composeapp.generated.resources.Res
import currency_converter.composeapp.generated.resources.logo
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