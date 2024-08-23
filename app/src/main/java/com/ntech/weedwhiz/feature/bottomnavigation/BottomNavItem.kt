package com.ntech.weedwhiz.feature.bottomnavigation

import com.ntech.weedwhiz.R
import com.ntech.weedwhiz.core.RouteName

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.logo, RouteName.HISTORY_SCREEN)
    object Setting : BottomNavItem("Setting", R.drawable.logo, RouteName.CONFIG_SCREEN)
    object Monitoring : BottomNavItem("Monitoring", R.drawable.logo, RouteName.MONITORING_SCREEN)

}