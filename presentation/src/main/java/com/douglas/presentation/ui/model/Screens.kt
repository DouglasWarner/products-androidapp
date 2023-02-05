package com.douglas.presentation.ui.model

sealed class Screens(val route: String) {
    object ProductList : Screens(route = "productList")
    object ProductDetail : Screens(route = "productDetail")
}