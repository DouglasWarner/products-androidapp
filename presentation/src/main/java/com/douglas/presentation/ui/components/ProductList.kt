package com.douglas.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
fun ProductList(
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel,
    onItemClick: (String) -> Unit = {}
) {
    val snackBarHost = remember { SnackbarHostState() }
    val uiState by productViewModel.uiState.collectAsState()
    val data by productViewModel.listProducts.collectAsState()

    val listProductMap by remember { derivedStateOf { data.map { it.key } } }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text("Products")
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHost,
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize(),
            contentPadding = paddingValues,
        ) {
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
                items = listProductMap,
                key = { key -> key.hashCode() },
            ) { sku ->
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    onClick = {
                        onItemClick(sku)
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    ) {
                        Text(
                            text = "Sku $sku",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "See product detail")
                    }
                }
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