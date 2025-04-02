package com.github.mutantes.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mutantes.Currency
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.round

class MainViewModel : ViewModel() {
    private val _screenState: MutableStateFlow<ScreenState> =
        MutableStateFlow(ScreenState(Currency.BRAZILIAN_REAL, Currency.UNITED_STATES_DOLLAR))
    val screenState: StateFlow<ScreenState> = _screenState.asStateFlow()
    private val httpClient: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    fun swapCurrency() {
        viewModelScope.launch {
            _screenState.update { screenState ->
                screenState.copy(
                    firstCurrency = screenState.secondCurrency,
                    secondCurrency = screenState.firstCurrency,
                    rate = screenState.rate?.let { 1 / it },
                    convertedValue = null
                )
            }

            calculateRate(
                _screenState.value.originalValue ?: 0.0,
                firstValue = true
            )
        }
    }


    fun getRates(currency: Currency,value: Double? = null) {
        viewModelScope.launch {
            val response =
                httpClient.get("https://api.fxratesapi.com/latest?base=${currency.currencyCode}")
                    .body<RatesResponse>()
            _screenState.update { screenState ->
                screenState.copy(
                    ratesResponse = response,
                    rate = response.rates[_screenState.value.secondCurrency.currencyCode]
                )
            }
            value?.let {
                calculateRate(value,true)
            }
        }
    }

    fun setFirstCurrency(currency: Currency) {
        _screenState.update { screenState ->
            screenState.copy(firstCurrency = currency)
        }
        getRates(currency,_screenState.value.originalValue)
    }

    fun setSecondCurrency(currency: Currency) {
        _screenState.update { screenState ->
            screenState.copy(secondCurrency = currency,
                rate = _screenState.value.ratesResponse?.rates[currency.currencyCode]
            )
        }
        calculateRate(_screenState.value.originalValue ?: 0.0, true)
    }

    fun calculateRate(value: Double, firstValue: Boolean) {
        _screenState.value.ratesResponse?.let {
            val result = if (firstValue) value * (_screenState.value.rate
                ?: 0.0) else value / (_screenState.value.rate ?: 0.0)
            _screenState.update { screenState ->
                screenState.copy(
                    changeFirstInput = firstValue,
                    convertedValue = round(result * 100) / 100,
                    originalValue = value
                )
            }
        }
    }
}