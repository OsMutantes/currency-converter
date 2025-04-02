package com.github.mutantes.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mutantes.Currency
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
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
        _screenState.update { screenState ->
            screenState.copy(
                firstCurrency = _screenState.value.secondCurrency,
                secondCurrency = _screenState.value.firstCurrency
            )
        }
        calculateRate(_screenState.value.originalValue ?: 0.0, firstValue = true, inverted = true)
    }

    fun getRates(currency: Currency) {
        viewModelScope.launch {
            val response =
                httpClient.get("https://api.fxratesapi.com/latest?base=${currency.currencyCode}")
                    .body<RatesResponse>()
            _screenState.update { screenState ->
                screenState.copy(ratesResponse = response)
            }
            println(response)
        }
    }

    fun calculateRate(value: Double, firstValue: Boolean, inverted: Boolean = false) {
        _screenState.value.ratesResponse?.let {
            println(it.rates)
            //TODO : bug na linha 54 ao inverter com o swap valor nao e calculado com o rate certo
            val rate = if (inverted) it.rates[_screenState.value.firstCurrency.currencyCode] else it.rates[_screenState.value.secondCurrency.currencyCode]
            println("Rate que vai ser calculado : $rate")
            val result = if (firstValue && inverted.not()) value * (rate ?: 0.0) else value / (rate ?: 0.0)
            println("")
            _screenState.update { screenState ->
                screenState.copy(
                    changeFirstInput = firstValue,
                    convertedValue = round(result * 100) / 100,
                    originalValue = value
                )
            }
            println(result)
        }
    }
}