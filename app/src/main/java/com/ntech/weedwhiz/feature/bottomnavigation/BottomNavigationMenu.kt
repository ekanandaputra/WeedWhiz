package com.ntech.weedwhiz.feature.bottomnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ntech.weedwhiz.core.theme.Black
import com.ntech.weedwhiz.core.theme.Gray
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.core.theme.bottomNavigationColor
import com.ntech.weedwhiz.core.theme.colorPrimary

@Composable
fun BottomNavigationMenu(
    navController: NavController,
    selectedMenuState: BottomNavItem,
    onItemClicked: (BottomNavItem) -> Unit,
) {
    val items = ArrayList<BottomNavItem>()

    items.add(BottomNavItem.Home)
    items.add(BottomNavItem.Monitoring)
    items.add(BottomNavItem.Setting)

    NavigationBar(
        containerColor = bottomNavigationColor,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .height(64.dp)
            .shadow(4.dp, RoundedCornerShape(32.dp), clip = true)
            .graphicsLayer {
                shape = RoundedCornerShape(32.dp)
                clip = true
            }.background(Color.Transparent)
    ) {
        items.forEach { item ->
            AddItem(
                screen = item,
                navController = navController,
                selectedMenuState,
                onItemClicked = { onItemClicked.invoke(item) }
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    navController: NavController,
    selectedMenuState: BottomNavItem,
    onItemClicked: (BottomNavItem) -> Unit,
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title, style = Typography.labelSmall)
        },
        icon = {
            Icon(
                painterResource(id = screen.icon),
                tint = if (selectedMenuState == screen) colorPrimary else Gray,
                contentDescription = screen.title,
                modifier = Modifier
                    .size(16.dp)
                    .padding(bottom = 4.dp)
            )
        },
        selected = false,
        alwaysShowLabel = true,
        onClick = {
            onItemClicked.invoke(screen)
            navController.navigate(screen.screen_route) {
                navController.graph.startDestinationRoute?.let { screen_route ->
                    popUpTo(screen_route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = NavigationBarItemDefaults.colors(Black)
    )
}
