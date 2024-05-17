package com.example.aceshop.ui.theme

sealed class Screen(val route: String) {

    object ShoppingHomePage: Screen(route = "shopping_home")
    object Cart: Screen(route = "cart")
    object Process: Screen(route = "process")
    object Racket: Screen(route = "racket")
    object Shuttlecock: Screen(route = "shuttlecock")
    object ItemSelectedRacket : Screen(route = "selectedRacket")
    object ItemSelectedShuttlecock : Screen(route = "selectedShuttlecock")
    object SellAllProduct : Screen(route = "sellAllProduct")
    object Order : Screen(route = "order")





}