//package com.example.aceshop.ui.theme
//
//import Cart
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.aceshop.Process
//import com.example.aceshop.ShoppingHomePage
//import com.example.aceshop.viewmodel.ViewModel
//
//@Composable
//fun NavGraph(
//    navController: NavHostController)
//{
//
//val racketViewModel:ViewModel = viewModel()
//    NavHost(
//        navController = navController,
//        startDestination = Screen.ShoppingHomePage.route
//    ) {
//
//        composable(Screen.ShoppingHomePage.route) {
//            ShoppingHomePage(modifier = Modifier,navController,racketViewModel)
//        }
//        composable(Screen.Cart.route) {
////            Cart(modifier = Modifier,navController, racketViewModel)
//            Cart(modifier = Modifier,navController,racketViewModel)
//
//        }
//        composable(Screen.Process.route) {
//            Process(modifier = Modifier,navController)
//        }
//    }
//}
