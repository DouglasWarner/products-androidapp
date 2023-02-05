package com.douglas.presentation.ui.navgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.douglas.presentation.ui.components.ProductDetail
import com.douglas.presentation.ui.components.ProductList
import com.douglas.presentation.ui.model.Screens
import com.douglas.presentation.ui.viewmodel.ProductViewModel

@Composable
fun ProductsNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.ProductList.route
) {
    val productViewModel: ProductViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.ProductList.route) {
            ProductList(
                modifier = Modifier
                    .fillMaxSize(),
                productViewModel = productViewModel
            ) { productId ->
                navController.navigate(route = Screens.ProductDetail.route)

                productViewModel.onSelectProduct(productId)
            }
        }

        composable(
            route = Screens.ProductDetail.route,
        ) {
            ProductDetail(
                modifier = Modifier.fillMaxSize(),
                productViewModel = productViewModel
            ) {
                navController.popBackStack()
            }
        }
    }
}