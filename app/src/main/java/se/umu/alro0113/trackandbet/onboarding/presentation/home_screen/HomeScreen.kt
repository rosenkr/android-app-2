package se.umu.alro0113.trackandbet.onboarding.presentation.home_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import se.umu.alro0113.trackandbet.navigation.Screen

// TODO set up viewmodel for homescreen and move state from here to there,
// TODO call hiltViewModel here and use state/set its state
// TODO be consistent with other screen packages, have a separate HomeScreenState file
@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Surface( // background color is defaulted to .surface which is same as .background
        modifier = Modifier.fillMaxSize(),
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar() {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route)
                            },
                            icon = {
                                if(index == selectedItemIndex){
                                    Icon(
                                        imageVector = item.selectedIcon,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(25.dp)
                                    )
                                } else {
                                    Icon(
                                        imageVector = item.unselectedIcon,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(25.dp)
                                    )
                                }
                            },
                            label = { Text(text = item.title) }
                        )
                    }
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues).border(2.dp, Color.Red)){
                Text(text = "content")
            }
        }
    }
}

val items = listOf(
    BottomNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, Screen.HomeScreen),
    BottomNavigationItem("Placeholder", Icons.Filled.Build, Icons.Outlined.Build, Screen.HomeScreen),
    BottomNavigationItem("Tickers", Icons.Filled.Search, Icons.Outlined.Search, Screen.TickersScreen)
)

// but see NavigationBarItem M3 composable? Lackner first has this then + NavigationBarItem later
data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector, // Icons.Filled ..
    val unselectedIcon: ImageVector, // Icons.Outlined.
    val route: Screen
)