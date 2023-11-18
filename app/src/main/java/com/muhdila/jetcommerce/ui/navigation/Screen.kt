package com.muhdila.jetcommerce.ui.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Cart : Screen("cart")
    data object Profile : Screen("profile")
    data object Detail : Screen("home/{detailId}") {
        fun createRoute(detailId: Long) = "home/$detailId"
    }
}