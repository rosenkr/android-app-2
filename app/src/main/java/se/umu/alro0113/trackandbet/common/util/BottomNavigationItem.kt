package se.umu.alro0113.trackandbet.common.util

import androidx.compose.ui.graphics.vector.ImageVector
import se.umu.alro0113.trackandbet.navigation.Screen

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: Screen
)