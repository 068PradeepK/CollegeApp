package com.pk.collegeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pk.collegeapp.admin.screens.AdminDashBoard
import com.pk.collegeapp.screens.About
import com.pk.collegeapp.screens.BottomNav
import com.pk.collegeapp.screens.Faculty
import com.pk.collegeapp.screens.Gallery
import com.pk.collegeapp.screens.Home

@Composable
fun NavGraph(navController: NavHostController) {
    val isAdmin = true
    NavHost(
        navController = navController,
        startDestination = if (isAdmin) Screens.AdminDashBoard.route else Screens.BottomNav.route
    ) {
        composable(Screens.BottomNav.route) {
            BottomNav(navController)
        }
        composable(Screens.Home.route) {
            Home()
        }
        composable(Screens.About.route) {
            About()
        }
        composable(Screens.Faculty.route) {
            Faculty()
        }
        composable(Screens.Gallery.route) {
            Gallery()
        }
        composable (Screens.AdminDashBoard.route) {
            AdminDashBoard(navController)
        }
    }
}