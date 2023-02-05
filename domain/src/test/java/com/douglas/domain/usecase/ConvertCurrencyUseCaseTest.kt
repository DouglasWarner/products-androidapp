package com.douglas.domain.usecase

import com.douglas.domain.util.fakeRateList
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ConvertCurrencyUseCaseTest {

    private lateinit var convertCurrencyUseCase: ConvertCurrencyUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        convertCurrencyUseCase = ConvertCurrencyUseCase()
    }

    @Test
    fun `conversion EUR to EUR`() = runTest {
        val rateExpected = 1.0
        val rateConversion = convertCurrencyUseCase("EUR", "EUR", fakeRateList)

        assert(rateConversion.isSuccess)

        val rateFound = rateConversion.getOrThrow()
        assert(rateFound.rate == rateExpected) {
            "Expected $rateExpected | Result $rateFound"
        }
    }

    @Test
    fun `conversion JPY to EUR`() = runTest {
        val rateExpected = 0.0070079999999999995
        val rateConversion = convertCurrencyUseCase("JPY", "EUR", fakeRateList)

        assert(rateConversion.isSuccess)

        val rateFound = rateConversion.getOrThrow()
        assert(rateFound.rate == rateExpected) {
            "Expected $rateExpected | Result $rateFound"
        }
    }

    @Test
    fun `conversion RUB to EUR`() = runTest {
        val rateExpected = 0.0156672
        val rateConversion = convertCurrencyUseCase("RUB", "EUR", fakeRateList)

        assert(rateConversion.isSuccess)

        val rateFound = rateConversion.getOrThrow()
        assert(rateFound.rate == rateExpected) {
            "Expected $rateExpected | Result $rateFound"
        }
    }

    @Test
    fun `no conversion found for PESETAS to EUR`() = runTest {
        val rateConversion = convertCurrencyUseCase("PESETAS", "EUR", fakeRateList)

        assert(rateConversion.isFailure)
    }
}