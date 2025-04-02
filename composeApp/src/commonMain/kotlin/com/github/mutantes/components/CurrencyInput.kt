package com.github.mutantes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.KeyboardType
import currency_converter.composeapp.generated.resources.ic_arrowdown
import org.jetbrains.compose.resources.painterResource

@Composable
fun CurrencyInput(
    currency: Currency,
    currencyValue: String,
    changeInput: Boolean,
    onValueChange: (value: String) -> Unit,
    onCurrencyChange: (currency: Currency) -> Unit
) {
    var value by remember { mutableStateOf("1,00") }
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    Box(
        modifier =
            Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(
                    2.dp,
                    color = if (isFocused) Colors.purpleBase else Colors.gray300,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = currency.symbol,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(Res.font.inter_regular)),
                        fontSize = 16.sp,
                        color = Colors.gray100
                    )
                )
                Spacer(Modifier.width(4.dp))
                BasicTextField(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    value = if (changeInput && currencyValue != "null") currencyValue.replace(
                        '.',
                        ','
                    ) else value,
                    onValueChange = {
                        if (it.matches(Regex("^[0-9,]*$"))) {
                            value = it
                            onValueChange(it)
                        }
                    },
                    textStyle = TextStyle(
                        fontFamily = FontFamily(Font(Res.font.inter_regular)),
                        fontSize = 16.sp,
                        color = Colors.gray100
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
            CurrencySelector(modifier = Modifier.weight(0.6f), currency) { onCurrencyChange(it) }
        }
    }
}

@Composable
private fun CurrencySelector(
    modifier: Modifier,
    selectedCurrency: Currency,
    onCurrencySelected: (Currency) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val boxWidth = remember { mutableStateOf(0.dp) }

    Box(modifier = modifier.onGloballyPositioned {
        boxWidth.value = it.size.width.dp
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Divider(modifier = Modifier.height(25.dp).width(1.dp), color = Colors.gray300)
            Spacer(modifier = Modifier.width(16.dp))
            CurrencyFlag(
                modifier = Modifier.size(30.dp).clip(CircleShape),
                countryCode = selectedCurrency.countryCode
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = selectedCurrency.currencyCode,
                style = TextStyle(
                    fontFamily = FontFamily(Font(Res.font.inter_regular)),
                    fontSize = 16.sp,
                    color = Colors.gray100
                )
            )
            Image(painter = painterResource(Res.drawable.ic_arrowdown), contentDescription = null)
        }

        DropdownMenu(
            modifier = Modifier
                .heightIn(max = 200.dp)
                .width(boxWidth.value),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            Column {
                Currency.entries.forEach { currency ->
                    DropdownMenuItem(onClick = {
                        onCurrencySelected(currency)
                        expanded = false
                    }) {
                        Row {
                            Spacer(modifier = Modifier.width(2.dp))
                            CurrencyFlag(
                                modifier = Modifier.size(30.dp).clip(CircleShape),
                                countryCode = currency.countryCode
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = currency.currencyCode, color = Colors.gray100)
                        }
                    }
                }
            }
        }
    }
}
