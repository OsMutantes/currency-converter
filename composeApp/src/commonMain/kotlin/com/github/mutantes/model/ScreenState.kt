package com.github.mutantes.model

import com.github.mutantes.Currency

data class ScreenState(
    val firstCurrency: Currency,
    val secondCurrency: Currency,
    val firstCurrencyValue: Double = 0.0,
    val secondCurrencyValue: Double = 0.0,
    val ratesResponse: RatesResponse? = null
)