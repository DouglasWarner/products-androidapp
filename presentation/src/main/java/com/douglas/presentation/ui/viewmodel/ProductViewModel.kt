package com.douglas.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas.domain.model.Rate
import com.douglas.domain.model.Transaction
import com.douglas.domain.repository.ProductRepository
import com.douglas.domain.usecase.ConvertCurrencyUseCase
import com.douglas.domain.usecase.GetTransactionsGroupByProductUseCase
import com.douglas.presentation.ui.model.ProductDetailDtv
import com.douglas.presentation.ui.model.UIState
import com.douglas.products.presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionsGroupByProductUseCase,
    private val getConversionRateUseCase: ConvertCurrencyUseCase,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _listRates = MutableStateFlow<List<Rate>>(emptyList())

    private val _uiState = MutableStateFlow<UIState>(UIState.Idle)
    val uiState get() = _uiState.asStateFlow()

    private val _listProducts = MutableStateFlow<Map<String, List<Transaction>>>(hashMapOf())
    val listProducts get() = _listProducts.asStateFlow()

    private val _productDetailDtv = MutableStateFlow(ProductDetailDtv("", emptyList(), "0.0"))
    val productDetailDtv get() = _productDetailDtv.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            loadData()

            loadListRates()
        }
    }

    private suspend fun loadListRates() {
        withContext(Dispatchers.IO) {
            productRepository.getRates().onSuccess {
                _listRates.value = it
            }
        }
    }

    private suspend fun loadData() {
        _uiState.value = UIState.Loading

        val result = withContext(Dispatchers.IO) {
            getTransactionUseCase()
        }

        result.onSuccess { data ->
            _listProducts.value = data
            _uiState.value = UIState.Idle
        }.onFailure {
            _uiState.value = UIState.Error(R.string.error_network)
        }
    }

    fun onSelectProduct(productId: String) = viewModelScope.launch {
        var totalAmount = 0.0
        for (transaction in _listProducts.value[productId].orEmpty()) {
            val rate = getConversionRateUseCase(transaction.currency, "EUR", _listRates.value).getOrNull()

            totalAmount += transaction.amount.toDouble() * (rate?.rate ?: 1.0)
        }

        _productDetailDtv.value = ProductDetailDtv(
            sku = productId,
            listTransaction = _listProducts.value[productId].orEmpty().map { "${it.amount} ${it.currency}" },
            totalAmount = totalAmount.toBigDecimal().setScale(2, RoundingMode.HALF_UP).toString()
        )
    }
}