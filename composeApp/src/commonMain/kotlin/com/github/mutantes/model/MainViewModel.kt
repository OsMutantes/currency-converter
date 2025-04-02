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
        _screenState.value = ScreenState(
            firstCurrency = _screenState.value.secondCurrency,
            secondCurrency = _screenState.value.firstCurrency
        )
    }

    fun getRates(currency: Currency) {
        viewModelScope.launch {
            val response =
                httpClient.get("https://api.fxratesapi.com/latest?base=${currency.currencyCode}").body<RatesResponse>()
            _screenState.update { screenState ->
                screenState.copy(ratesResponse = response)
            }
            println(response)
        }
    }

    fun calculateRate(value: Double) {
        _screenState.value.ratesResponse?.let {
            _screenState.update {
                screenState ->
                screenState.copy(firstCurrencyValue = value)
            }
            val result = value*(it.rates[_screenState.value.secondCurrency.currencyCode] ?:0.0)
            println(result)
        }
    }
}