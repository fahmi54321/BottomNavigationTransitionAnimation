package com.android.bottomnavigationtransitionanimation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Home : Screen("home", Icons.Outlined.Home, "Home")
    object Cart : Screen("cart", Icons.Outlined.ShoppingCart, "Cart")
    object Notificatios : Screen("notifications", Icons.Outlined.Notifications, "Notifications")
    object More : Screen("more", Icons.Outlined.MoreVert, "More")
    object Profile : Screen("profile", Icons.Outlined.Person, "Profile")
    object Email : Screen("email", Icons.Outlined.Email, "Email")
    object Phone : Screen("phone", Icons.Outlined.Phone, "Phone")
}
