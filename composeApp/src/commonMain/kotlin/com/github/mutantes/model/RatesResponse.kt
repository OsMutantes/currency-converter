package com.github.mutantes.model

import kotlinx.serialization.Serializable

@Serializable
data class RatesResponse(
    val success: Boolean,
    val terms: String,
    val privacy: String,
    val timestamp: Long,
    val date: String,
    val base: String,
    val rates: Map<String, Double>
)