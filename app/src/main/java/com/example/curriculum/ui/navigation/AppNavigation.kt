package com.example.curriculum.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.curriculum.ui.home.HomeScreen
import com.example.curriculum.ui.personal.PersonalScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(onNavigate = { route ->
                navController.navigate(route)
            })
        }
        composable(Screen.Personal.route) {
            PersonalScreen(onBack = {
                navController.popBackStack()
            })
        }
        composable(Screen.Experience.route) {
            Text("Experiências")
        }
        composable(Screen.Courses.route) {
            Text("Diplomas e cursos")
        }
        composable(Screen.Others.route) {
            Text("Outros")
        }
    }
}
