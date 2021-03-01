package com.example.simplecurrencyconverterapp.data.models

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: Rates
)
