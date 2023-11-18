package com.muhdila.jetcommerce

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.muhdila.jetcommerce.ui.navigation.NavigationItem
import com.muhdila.jetcommerce.ui.navigation.Screen
import com.muhdila.jetcommerce.ui.screen.cart.CartScreen
import com.muhdila.jetcommerce.ui.screen.detail.DetailScreen
import com.muhdila.jetcommerce.ui.screen.home.HomeScreen
import com.muhdila.jetcommerce.ui.screen.profile.ProfileScreen
import com.muhdila.jetcommerce.ui.theme.JetCommerceTheme
import com.muhdila.jetcommerce.ui.theme.myFont2

@Composable
fun JetCommerceApp(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        )
        {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { itemId ->
                        navController.navigate(Screen.Detail.createRoute(itemId))
                    },
                    navController = navController
                )
            }
            composable(Screen.Cart.route) {
                val context = LocalContext.current
                CartScreen(
                    onOrderButtonClicked = { message ->
                        shareOrder(context, message)
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("detailId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("detailId") ?: -1L
                DetailScreen(
                    itemsId = id,
                    navigateBack = { navController.navigateUp() },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = Color.DarkGray,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = R.drawable.ic_home,
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_cart),
                icon = R.drawable.ic_shopping_cart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = R.drawable.ic_user,
                screen = Screen.Profile,
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    val iconPainter: Painter = painterResource(id = item.icon)
                    Icon(
                        painter = iconPainter,
                        contentDescription = item.title,
                        modifier = Modifier.testTag("${item.screen.route}_Icon")
                    )
                },
                label = {
                    Text(
                        item.title,
                        fontFamily = myFont2,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
            )
        }
    }
}

private fun shareOrder(context: Context, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
        putExtra(Intent.EXTRA_TEXT, summary)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.app_name)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetCommerceTheme {
        JetCommerceApp()
    }
}
