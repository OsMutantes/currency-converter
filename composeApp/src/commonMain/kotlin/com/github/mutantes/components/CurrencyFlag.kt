package com.github.mutantes.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun CurrencyFlag(modifier: Modifier = Modifier, countryCode: String) {
    KamelImage(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        resource = asyncPainterResource(data = "https://flagcdn.com/w320/${countryCode.lowercase()}.png"),
        contentDescription = null
    )
}