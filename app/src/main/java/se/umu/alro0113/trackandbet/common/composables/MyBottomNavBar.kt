package se.umu.alro0113.trackandbet.common.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import se.umu.alro0113.trackandbet.common.util.BottomNavigationItem

@Composable
fun MyBottomNavBar(items : List<BottomNavigationItem>, selectedItemIndex : Int, navController : NavHostController){
    Column {
        HorizontalDivider(thickness = 1.5.dp)
        GradientBottom {
            NavigationBar(containerColor = Color.Transparent) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = {
                            // either pops back to an existing entry for this route, or navigate and add new entry
                            // if no such entry was found to pop back to
                            if (!navController.popBackStack(item.route, inclusive = false)) { // only create new entry if we had to pop anything from the back stack in order to
                                navController.navigate(item.route) {
                                    launchSingleTop = true // avoid recreating viewmodel if user clicks on the same item multiple times
                                }
                            }
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
    }
}

// Bottom nav bar gradient towards system bottom bar
@Composable
fun GradientBottom(content: @Composable () -> Unit) {
    val bottomNavBarDefaultBackground =  MaterialTheme.colorScheme.surfaceContainer //NavigationBarDefaults.containerColor

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        bottomNavBarDefaultBackground,
                        MaterialTheme.colorScheme.surfaceBright
                    )
                )
            )
    ) {
        content()
    }
}
