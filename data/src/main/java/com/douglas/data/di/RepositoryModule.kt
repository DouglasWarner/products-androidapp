package com.douglas.data.di

import com.douglas.data.repository.ProductRepositoryImpl
import com.douglas.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
}
