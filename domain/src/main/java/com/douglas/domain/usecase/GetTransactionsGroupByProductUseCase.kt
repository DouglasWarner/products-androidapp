package com.douglas.domain.usecase

import com.douglas.domain.model.Transaction
import com.douglas.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTransactionsGroupByProductUseCase(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Result<Map<String, List<Transaction>>> {
        val result = productRepository.getProducts().getOrElse {
            return Result.failure(it)
        }
        val groupingBy = withContext(Dispatchers.Default) {
            result.groupBy { it.sku }
        }
        return Result.success(groupingBy)

    }

}