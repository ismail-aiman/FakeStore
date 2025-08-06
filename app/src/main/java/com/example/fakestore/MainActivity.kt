package com.example.fakestore
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.fakestore.ui.theme.FakeStoreTheme
import com.example.fakestore.ui.theme.Screen
import data.ProductViewModel
import ui.DetailScreen
import ui.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeStoreTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Screen.Home.route) {
                    composable(Screen.Home.route) {
                        val vm: ProductViewModel = viewModel()
                        HomeScreen(vm, navController)
                    }
                    composable(
                        Screen.Detail.route,
                        arguments = listOf(navArgument("productId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                        val vm: ProductViewModel = viewModel()
                        DetailScreen(productId, vm)
                    }
                }
            }
        }
    }
}