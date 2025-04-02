package com.github.mutantes.model

import com.github.mutantes.Currency

data class ScreenState(
    val firstCurrency: Currency,
    val secondCurrency: Currency,
    val originalValue: Double? = null,
    val convertedValue: Double? = 1.0,
    val changeFirstInput: Boolean = false,
    val rate: Double? = null,
    val ratesResponse: RatesResponse? = null
)