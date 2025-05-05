package com.github.mutantes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import currency_converter.composeapp.generated.resources.Res
import currency_converter.composeapp.generated.resources.heart
import currency_converter.composeapp.generated.resources.inter_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Footer() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = "Made with ", style = TextStyle(
                    fontFamily = FontFamily(Font(Res.font.inter_regular)),
                    fontSize = 14.sp
                )
            )
            Image(
                modifier = Modifier.size(18.dp),
                painter = painterResource(Res.drawable.heart),
                contentDescription = null
            )
            Text(
                text = " using Compose Multiplatform", style = TextStyle(
                    fontFamily = FontFamily(Font(Res.font.inter_regular)),
                    fontSize = 14.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Copyright © 2025. All rights are reserved.", style = TextStyle(
                fontFamily = FontFamily(Font(Res.font.inter_regular)),
                fontSize = 18.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview
private fun FooterPreview() {
    Surface {
        Footer()
    }
}