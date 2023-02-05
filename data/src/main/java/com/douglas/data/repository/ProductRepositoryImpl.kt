package com.douglas.data.repository

import com.douglas.data.datasource.remote.ApiService
import com.douglas.data.mapper.toDomain
import com.douglas.domain.repository.ProductRepository
import com.douglas.domain.model.Rate
import com.douglas.domain.model.Transaction
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): ProductRepository {

    override suspend fun getProducts(): Result<List<Transaction>> = runCatching { apiService.getTransactions().map { it.toDomain() } }

    override suspend fun getRates(): Result<List<Rate>> = runCatching { apiService.getRates().map { it.toDomain() } }
}