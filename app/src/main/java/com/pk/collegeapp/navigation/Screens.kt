package com.pk.collegeapp.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object About : Screens("about")
    object Faculty : Screens("faculty")
    object Gallery : Screens("gallery")
    object BottomNav : Screens("bottomNav")
    object AdminDashBoard: Screens("adminDashBoard")
}