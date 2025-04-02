package com.github.mutantes.model

import com.github.mutantes.Currency

data class ScreenState(
    val firstCurrency: Currency,
    val secondCurrency: Currency,
    val originalValue: Double? = null,
    val convertedValue: Double? = null,
    val changeFirstInput: Boolean = false,
    val ratesResponse: RatesResponse? = null
)