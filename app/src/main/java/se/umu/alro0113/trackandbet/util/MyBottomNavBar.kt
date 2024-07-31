package se.umu.alro0113.trackandbet.util

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun MyBottomNavBar(items : List<BottomNavigationItem>, selectedItemIndex : Int, navController : NavHostController){
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
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