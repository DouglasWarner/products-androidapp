package com.douglas.data.mapper

import com.douglas.data.datasource.remote.model.RateDto
import com.douglas.data.datasource.remote.model.TransactionDto
import com.douglas.domain.model.Rate
import com.douglas.domain.model.Transaction
import java.math.RoundingMode
import kotlin.math.sign
import kotlin.math.withSign

fun RateDto.toDomain() = Rate(
    from = from ?: "EUR",
    to = to ?: "EUR",
    rate = rate ?: 1.0
)

fun TransactionDto.toDomain() = Transaction(
    sku = sku ?: "-1",
    amount = (amount?.toBigDecimalOrNull()?.setScale(2, RoundingMode.HALF_UP) ?: "0.0").toString(),
    currency = currency ?: "UNKNOWN"
)