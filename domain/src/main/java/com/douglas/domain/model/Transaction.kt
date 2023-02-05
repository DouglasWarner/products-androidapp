package com.douglas.domain.model

data class Transaction(
    val amount: String,
    val currency: String,
    val sku: String
)