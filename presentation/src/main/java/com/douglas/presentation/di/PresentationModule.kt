package com.douglas.presentation.di

import com.douglas.domain.repository.ProductRepository
import com.douglas.domain.usecase.ConvertCurrencyUseCase
import com.douglas.domain.usecase.GetTransactionsGroupByProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    fun provideGetTransactionsGroupByProductUseCase(productRepository: ProductRepository): GetTransactionsGroupByProductUseCase =
        GetTransactionsGroupByProductUseCase(productRepository = productRepository)

    @Provides
    fun provideGetConversionRateUseCase(): ConvertCurrencyUseCase = ConvertCurrencyUseCase()
}