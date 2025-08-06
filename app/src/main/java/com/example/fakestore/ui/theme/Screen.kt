package com.example.fakestore.ui.theme

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{productId}"){
        fun createRoute(productId: Int)= "detail/${productId}"
    }

}