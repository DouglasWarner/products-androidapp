package com.douglas.data.datasource.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionDto(
    val amount: String?,
    val currency: String?,
    val sku: String?
) : Parcelable