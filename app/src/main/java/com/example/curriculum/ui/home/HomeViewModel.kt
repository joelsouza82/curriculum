package com.example.curriculum.ui.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.example.curriculum.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class HomeSection(
    val title: String,
    val icon: ImageVector,
    val route: String
)

class HomeViewModel : ViewModel() {
    private val _sections = MutableStateFlow<List<HomeSection>>(emptyList())
    val sections: StateFlow<List<HomeSection>> = _sections

    init {
        loadData()
    }

    private fun loadData() {
        _sections.value = listOf(
            HomeSection("Dados Pessoais", Icons.Default.Person, Screen.Personal.route),
            HomeSection("Experiências", Icons.Default.BusinessCenter, Screen.Experience.route),
            HomeSection("Diplomas e Cursos", Icons.Default.School, Screen.Courses.route),
            HomeSection("Outras Informações", Icons.Default.MoreHoriz, Screen.Others.route)
        )
    }
}
