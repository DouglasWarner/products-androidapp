package com.douglas.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.douglas.presentation.ui.model.UIState
import com.douglas.presentation.ui.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetail(
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel,
    onBackPressed: () -> Unit = {}
) {
    val snackBarHost = remember { SnackbarHostState() }
    val uiState by productViewModel.uiState.collectAsState()
    val productSelected by productViewModel.productDetailDtv.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Product ${productSelected.sku}")
                },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return")
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHost,
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize(),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .animateContentSize(),
                contentPadding = paddingValues,
            ) {
                item {
                    AnimatedVisibility(visible = productSelected.listTransaction.isEmpty()) {
                        Box(
                            modifier = modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("There aren't transaction for this product")
                        }
                    }
                }

                item {
                    if (uiState is UIState.Loading) {
                        Box(
                            modifier = Modifier
                                .padding(paddingValues)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                items(
                    items = productSelected.listTransaction,
                    key = { key -> key.hashCode() },
                ) { transaction ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                        ) {
                            Text(
                                text = transaction,
                                modifier = Modifier.padding(10.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }

            ElevatedCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Total amount: ${productSelected.totalAmount} €",
                    modifier = Modifier
                        .padding(10.dp)
                        .animateContentSize(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (uiState is UIState.Error) {
            val errorString = stringResource(id = (uiState as UIState.Error).error)
            LaunchedEffect(key1 = Unit) {
                snackBarHost.showSnackbar(
                    message = errorString
                )
            }
        }
    }

}