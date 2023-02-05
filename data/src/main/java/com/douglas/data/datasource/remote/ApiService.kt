package com.douglas.data.datasource.remote

import com.douglas.data.datasource.remote.model.RateDto
import com.douglas.data.datasource.remote.model.TransactionDto
import retrofit2.http.GET

interface ApiService {

    @GET("transactions")
    suspend fun getTransactions(): List<TransactionDto>

    @GET("rates")
    suspend fun getRates(): List<RateDto>
}