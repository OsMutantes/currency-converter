package com.github.mutantes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import currency_converter.composeapp.generated.resources.Res
import currency_converter.composeapp.generated.resources.arrows
import org.jetbrains.compose.resources.painterResource

@Composable
fun SwapButton(onClick : ()-> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Image(
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null, //tira o feedback visual
                    onClick = { onClick() }
                ),
            painter = painterResource(Res.drawable.arrows),
            contentDescription = null
        )
    }
}
