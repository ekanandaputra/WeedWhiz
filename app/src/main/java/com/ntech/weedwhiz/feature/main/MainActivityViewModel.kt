package com.ntech.weedwhiz.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ntech.weedwhiz.feature.bottomnavigation.BottomNavItem

class MainActivityViewModel() : ViewModel() {
    val selectedMenuState = mutableStateOf<BottomNavItem>(BottomNavItem.Detection)

    fun setSelectedMenu(item: BottomNavItem) {
        selectedMenuState.value = item
    }
}