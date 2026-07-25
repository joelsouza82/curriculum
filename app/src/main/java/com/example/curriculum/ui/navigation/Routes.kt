package com.example.curriculum.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Personal : Screen("personal")
    object Experience : Screen("experience")
    object Courses : Screen("courses")
    object Others : Screen("others")
}
