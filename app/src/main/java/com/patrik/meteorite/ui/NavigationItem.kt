package com.patrik.meteorite.ui

import com.patrik.meteorite.R

sealed class NavigationItem(val route: String, val icon: Int, val titleResource: Int) {
    object MapScreen : NavigationItem("map", R.drawable.meteorite, R.string.map_screen)
    object ListScreen : NavigationItem("list", R.drawable.list, R.string.list_screen)
}