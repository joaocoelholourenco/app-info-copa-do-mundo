package com.joaovitorcl13.consumeapijetpackcomposetutorial.ui.navigation

import com.joaovitorcl13.consumeapijetpackcomposetutorial.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object  MyMatches : NavigationItem("mymatch", R.drawable.ic_sports_soccer, "Minhas partdias")
}