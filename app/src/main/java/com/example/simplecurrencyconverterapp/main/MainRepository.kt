package com.example.simplecurrencyconverterapp.main

import com.example.simplecurrencyconverterapp.data.models.CurrencyResponse
import com.example.simplecurrencyconverterapp.utils.Resource

interface MainRepository {

    suspend fun getRates(base: String) : Resource<CurrencyResponse>

}