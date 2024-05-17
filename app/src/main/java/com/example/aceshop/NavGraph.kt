package com.example.aceshop.ui.theme

import Cart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aceshop.ItemDetailsRacket
import com.example.aceshop.ItemDetailsShuttlecock
import com.example.aceshop.Order
import com.example.aceshop.Process
import com.example.aceshop.SeeAll
import com.example.aceshop.ShoppingHomePage
import com.example.aceshop.ShowRacket
import com.example.aceshop.ShowShuttlecock
import com.example.aceshop.data.Racket
import com.example.aceshop.repo.CartFirebase
import com.example.aceshop.viewmodel.ViewModel

@Composable
fun NavGraph(
    navController: NavHostController)
{

    val racketViewModel:ViewModel = viewModel()
    val shuttlecockViewModel:ViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.ShoppingHomePage.route
    ) {

        composable(Screen.ShoppingHomePage.route) {
            ShoppingHomePage(modifier = Modifier, navController, racketViewModel,shuttlecockViewModel)
        }
        composable(Screen.Cart.route) {
            Cart(modifier = Modifier, navController, racketViewModel,shuttlecockViewModel)
        }
        composable(Screen.Process.route) {
            Process(modifier = Modifier, navController)
        }
        composable(Screen.Racket.route) {
            ShowRacket(modifier = Modifier, navController, racketViewModel)
        }
        composable(Screen.Shuttlecock.route) {
            ShowShuttlecock(modifier = Modifier, navController, shuttlecockViewModel )
        }
        composable(Screen.ItemSelectedRacket.route) {
            ItemDetailsRacket(modifier = Modifier, navController, racketViewModel, cartFirebase = CartFirebase()  )
        }
        composable(Screen.ItemSelectedShuttlecock.route) {
            ItemDetailsShuttlecock(modifier = Modifier, navController,shuttlecockViewModel)
        }
        composable(Screen.SellAllProduct.route) {
            SeeAll(modifier = Modifier, navController,shuttlecockViewModel,racketViewModel)
        }
        composable(Screen.Order.route) {
            Order(modifier = Modifier, navController)
        }
    }

}
