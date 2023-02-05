package com.douglas.domain.util

import com.douglas.domain.model.Rate

val fakeRateList = listOf(
    Rate(from = "EUR", to = "USD", rate = 1.04),
    Rate(from = "USD", to = "EUR", rate = 0.96),
    Rate(from = "GBP", to = "EUR", rate = 1.16),
    Rate(from = "JPY", to = "USD", rate = 0.0073),
    Rate(from = "AUD", to = "INR", rate = 55.29),
    Rate(from = "CAD", to = "USD", rate = 0.74),
    Rate(from = "SEK", to = "USD", rate = 0.096),
    Rate(from = "RUB", to = "SEK", rate = 0.17),
    Rate(from = "INR", to = "EUR", rate = 0.012)
)