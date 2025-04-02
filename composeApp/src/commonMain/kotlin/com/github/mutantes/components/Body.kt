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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mutantes.Currency
import com.github.mutantes.model.MainViewModel
import com.github.mutantes.style.Colors
import currency_converter.composeapp.generated.resources.Res
import currency_converter.composeapp.generated.resources.inter_regular
import currency_converter.composeapp.generated.resources.inter_semibold
import org.jetbrains.compose.resources.Font

@Composable
fun Body() {

    val homeViewModel = remember { MainViewModel() }
    val state = homeViewModel.screenState.collectAsState().value

    LaunchedEffect(Unit) { homeViewModel.getRates(state.firstCurrency) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .shadow(
                    elevation = 16.dp,
                    spotColor = Colors.purpleBase,
                    shape = RoundedCornerShape(12.dp),
                )
                .fillMaxWidth()
                .background(Colors.white)
                .height(318.dp)
                .clip(RoundedCornerShape(12.dp)),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
            ) {
                Text(
                    "Conversor de moedas", color = Colors.gray100, style = TextStyle(
                        fontFamily = FontFamily(Font(Res.font.inter_semibold)),
                        fontSize = 16.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Digite o valor e escolha as moedas de conversão",
                    color = Colors.gray200,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(Res.font.inter_regular)),
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(40.dp))
                CurrencyInput(
                    state.firstCurrency,
                    state.convertedValue.toString(),
                    state.changeFirstInput.not()
                ) {
                    if (it.isEmpty().not())
                    homeViewModel.calculateRate(it.replace(',','.').toDouble(), true)
                }
                Spacer(modifier = Modifier.height(12.dp))
                SwapButton(onClick = { homeViewModel.swapCurrency() })
                Spacer(modifier = Modifier.height(12.dp))
                CurrencyInput(
                    state.secondCurrency,
                    state.convertedValue.toString(),
                    state.changeFirstInput
                ) {
                    if (it.isEmpty().not())
                    homeViewModel.calculateRate(it.replace(',','.').toDouble(), false)
                }
                Text(state.rate.toString())
            }
        }
    }
}