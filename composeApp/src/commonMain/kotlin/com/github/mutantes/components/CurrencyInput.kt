package com.github.mutantes.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mutantes.Currency
import com.github.mutantes.style.Colors
import currency_converter.composeapp.generated.resources.Res
import currency_converter.composeapp.generated.resources.inter_regular
import org.jetbrains.compose.resources.Font

@Composable
fun CurrencyInput(currency: Currency) {
    Box(
        modifier =
            Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, color = Colors.gray300, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
    ) {
        Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "${currency.symbol} 1.000,00",
                style = TextStyle(
                    fontFamily = FontFamily(Font(Res.font.inter_regular)),
                    fontSize = 16.sp,
                    color = Colors.gray100
                )
            )
            CurrencySelector(currency)
        }
    }
}

@Composable
private fun CurrencySelector(currency: Currency) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Divider(modifier = Modifier.height(25.dp).width(1.dp), color = Colors.gray300)
        Spacer(modifier = Modifier.width(16.dp))
        CurrencyFlag(
            modifier = Modifier.size(30.dp).clip(CircleShape),
            countryCode = currency.countryCode
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = currency.currencyCode, style = TextStyle(
                fontFamily = FontFamily(Font(Res.font.inter_regular)),
                fontSize = 16.sp,
                color = Colors.gray100
            )
        )
    }
}