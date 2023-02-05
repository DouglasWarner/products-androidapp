package com.douglas.domain.usecase

import com.douglas.domain.Errors
import com.douglas.domain.model.Transaction
import com.douglas.domain.repository.ProductRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetTransactionsGroupByProductUseCaseTest {
    @MockK
    private lateinit var productRepository: ProductRepository

    private lateinit var getTransactionsGroupByProductUseCase: GetTransactionsGroupByProductUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getTransactionsGroupByProductUseCase = GetTransactionsGroupByProductUseCase(productRepository)
    }

    @Test
    fun `when list of products are empty`() = runTest {
        coEvery { productRepository.getProducts() }.returns(Result.success(listOf()))

        val result = getTransactionsGroupByProductUseCase()

        assert(result.isSuccess)

        assert(result.getOrThrow().isEmpty())
    }

    @Test
    fun `when list of products return just 1 product`() = runTest {
        val transactions = listOf(Transaction("0.0", "EUR", "T1000"))
        coEvery { productRepository.getProducts() }.returns(Result.success(transactions))

        val result = getTransactionsGroupByProductUseCase()

        assert(result.isSuccess)

        assert(result.getOrThrow().size == 1)
    }

    @Test
    fun `when there is an error`() = runTest {
        coEvery { productRepository.getProducts() }.returns(Result.failure(Errors.NoProductsList()))

        val result = getTransactionsGroupByProductUseCase()

        assert(result.isFailure)
    }

    @Test
    fun `when group a product with 3 transactions`() = runTest {
        val transactions = listOf(
            Transaction("0.0", "EUR", "T1000"),
            Transaction("0.0", "EUR", "T1000"),
            Transaction("0.0", "EUR", "T1000")
        )
        coEvery { productRepository.getProducts() }.returns(Result.success(transactions))

        val result = getTransactionsGroupByProductUseCase()

        assert(result.isSuccess)

        assert(result.getOrThrow().size == 1)
    }
}