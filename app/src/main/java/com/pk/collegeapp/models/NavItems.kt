package com.pk.collegeapp.models

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItems(
    val title: String,
    val icon: Int
)

data class BottomNavItems(
    val title: String,
    val icon: Int,
    val route: String
)
