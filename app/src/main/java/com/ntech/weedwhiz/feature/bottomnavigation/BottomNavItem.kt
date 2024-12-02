package com.ntech.weedwhiz.feature.bottomnavigation

import com.ntech.weedwhiz.R
import com.ntech.weedwhiz.core.RouteName

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Detection : BottomNavItem("Home", R.drawable.logo, RouteName.DETECTION_SCREEN)
    object History : BottomNavItem("History", R.drawable.logo, RouteName.HISTORY_SCREEN)
    object Monitoring : BottomNavItem("Monitoring", R.drawable.logo, RouteName.MONITORING_SCREEN)

}