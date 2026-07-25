package com.example.curriculum.ui.home

import org.junit.Assert.assertEquals
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun `when viewmodel initializes then it should have 4 sections`() {
        // Given
        val viewModel = HomeViewModel()

        // Then
        assertEquals(4, viewModel.sections.value.size)
    }

    @Test
    fun `when viewmodel initializes then sections should have correct titles`() {
        // Given
        val viewModel = HomeViewModel()
        val expectedTitles = listOf(
            "Dados Pessoais",
            "Experiências",
            "Diplomas e Cursos",
            "Outras Informações"
        )

        // Then
        val actualTitles = viewModel.sections.value.map { it.title }
        assertEquals(expectedTitles, actualTitles)
    }
}
