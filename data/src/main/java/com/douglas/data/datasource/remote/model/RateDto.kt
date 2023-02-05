package com.douglas.data.datasource.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RateDto(
    val from: String?,
    val to: String?,
    val rate: Double?
) : Parcelable