package com.android.bottomnavigationtransitionanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.android.bottomnavigationtransitionanimation.ui.theme.BottomNavigationTransitionAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationTransitionAnimationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        bottomBar = {
            MyBottomBar(navController)
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier
                .padding(padding)
        ) {
            composable(
                Screen.Home.route
            ) {
                HomeScreen()
            }

            composable(
                Screen.Cart.route
            ) {
                CartScreen()
            }

            composable(
                Screen.Notificatios.route
            ) {
                NotificationScreen()
            }

            navigation(
                route = Screen.More.route,
                startDestination = Screen.Profile.route
            ) {

                composable(
                    route = Screen.Profile.route
                ) {
                    ProfileScreen(navController)
                }

                composable(
                    Screen.Email.route
                ) {
                    EmailScreen()
                }

                composable(
                    Screen.Phone.route
                ) {
                    PhoneRoute()
                }
            }
        }
    }
}

val mainScreen = listOf(
    Screen.Home,
    Screen.Cart,
    Screen.Notificatios,
    Screen.More
)

@Composable
fun MyBottomBar(navController: NavHostController) {
    BottomNavigation {

        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry?.destination

        for (screen in mainScreen) {
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(
                            id = navController.graph.findStartDestination().id
                        ) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.title
                    )
                },
                label = {
                    Text(text = screen.title)
                }
            )
        }
    }
}

@Composable
fun PlaceHolderScreen(
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope. () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
        )

        Icon(
            imageVector = icon,
            contentDescription = title,
            modifier = Modifier
                .fillMaxSize()
        )

        content()
    }
}

@Composable
fun HomeScreen() {
    PlaceHolderScreen(
        title = Screen.Home.title,
        icon = Screen.Home.icon
    )
}

@Composable
fun CartScreen() {
    PlaceHolderScreen(
        title = Screen.Cart.title,
        icon = Screen.Cart.icon
    )
}

@Composable
fun NotificationScreen() {
    PlaceHolderScreen(
        title = Screen.Notificatios.title,
        icon = Screen.Notificatios.icon
    )
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .padding(top = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navController.navigate(Screen.Email.route) },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Email")
        }

        Button(
            onClick = { navController.navigate(Screen.Phone.route) },
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Phone")
        }
    }
}

@Composable
fun EmailScreen() {
    PlaceHolderScreen(
        title = Screen.Email.title,
        icon = Screen.Email.icon
    )
}

@Composable
fun PhoneRoute() {
    PlaceHolderScreen(
        title = Screen.Phone.title,
        icon = Screen.Phone.icon
    )
}

