package com.douglas.domain.repository

import com.douglas.domain.model.Rate
import com.douglas.domain.model.Transaction

interface ProductRepository {

    suspend fun getProducts(): Result<List<Transaction>>

    suspend fun getRates() : Result<List<Rate>>

}