package com.example.simplecurrencyconverterapp.main

import com.example.simplecurrencyconverterapp.data.CurrencyApi
import com.example.simplecurrencyconverterapp.data.models.CurrencyResponse
import com.example.simplecurrencyconverterapp.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val api : CurrencyApi
) : MainRepository{

    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()

            if (response.isSuccessful && result != null){
                Resource.Success(result)
            }else{
                Resource.Error(response.message())
            }
        }catch (e:Exception){
            Resource.Error(e.message ?: "An error occurred in get Rates from api!")
        }

    }
}