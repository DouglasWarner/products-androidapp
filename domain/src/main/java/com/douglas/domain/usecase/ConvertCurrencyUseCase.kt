package com.douglas.domain.usecase

import com.douglas.domain.Errors
import com.douglas.domain.model.Rate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConvertCurrencyUseCase {
    suspend operator fun invoke(from: String, to: String, conversionRates: List<Rate>): Result<Rate> {
        val rateResult = withContext(Dispatchers.Default) {
            val directConversion = conversionRates.firstOrNull { it.from == from && it.to == to }
            val sameCurrency = from == to

            when {
                sameCurrency -> 1.0
                directConversion != null -> directConversion.rate
                else -> {
                    val exchangeRateMap = hashMapOf<String, HashMap<String, Double>>()
                    conversionRates.forEach { node ->
                        if (!exchangeRateMap.containsKey(node.from)) {
                            exchangeRateMap[node.from] = HashMap()
                        }
                        exchangeRateMap[node.from]?.set(node.to, node.rate)
                        if (!exchangeRateMap.containsKey(node.to)) {
                            exchangeRateMap[node.to] = HashMap()
                        }
                        exchangeRateMap[node.to]?.set(node.from, 1.0 / node.rate)
                    }

                    var currentCurrency = from
                    var currentRateValue = 1.0
                    val currencyVisited = hashSetOf<String>()
                    while (currentCurrency.isNotEmpty()) {
                        if (!currencyVisited.add(currentCurrency)) {
                            currentCurrency = ""
                            continue
                        }
                        val nextRateValue = currentRateValue // Need to save in memory the next rate value
                        val nextCurrency = exchangeRateMap[currentCurrency]
                        nextCurrency?.keys?.forEach { key ->
                            if (!currencyVisited.contains(key)) {
                                if (key == to) {
                                    return@withContext (nextRateValue * nextCurrency[key]!!)
                                }
                                currentCurrency = key
                                currentRateValue = nextRateValue * nextCurrency[key]!!
                            }
                        }
                    }

                    -1.0
                }
            }
        }
        return when (rateResult) {
            -1.0 -> Result.failure(Errors.NoConversionFound())
            else -> Result.success(Rate(from = from, to = to, rate = rateResult))
        }

    }
}