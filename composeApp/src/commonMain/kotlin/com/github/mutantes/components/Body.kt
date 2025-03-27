package com.github.mutantes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.github.mutantes.style.Colors

@Composable
fun Body() {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center){
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(12.dp))
                .background(Colors.white)
                .height(318.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 24.dp),
                verticalArrangement = Arrangement.Top,
                ) {
                Text("Conversor de Moedas", color = Colors.gray100)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Digite o valor e escolha as moedas de conversão", color = Colors.gray100)
            }
        }
    }
}