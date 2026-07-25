package com.example.curriculum.ui.personal

import app.cash.turbine.test
import com.example.curriculum.data.model.Personal
import com.example.curriculum.data.remote.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PersonalViewModelTest {

    private val apiService = mockk<ApiService>()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when loadData is called and api returns data then it should emit Success state`() = runTest {
        // Given
        val mockPersonal = Personal(
            id = 1,
            address = "Rua Teste",
            city = "Feira de Santana",
            neighborhood = "Centro",
            state = "BA",
            cep = "44000-000",
            phone = "75999999999",
            email = "test@test.com",
            website = null,
            linkedin = null,
            github = null
        )
        coEvery { apiService.fetchPersonal() } returns listOf(mockPersonal)

        // When
        val viewModel = PersonalViewModel(apiService)

        // Then
        viewModel.uiState.test {
            assertEquals(PersonalUiState.Loading, awaitItem()) // First state
            testDispatcher.scheduler.advanceUntilIdle()
            val state = awaitItem()
            assert(state is PersonalUiState.Success)
            assertEquals(mockPersonal, (state as PersonalUiState.Success).data)
        }
    }

    @Test
    fun `when loadData is called and api fails then it should emit Error state`() = runTest {
        // Given
        val errorMessage = "API Error"
        coEvery { apiService.fetchPersonal() } throws Exception(errorMessage)

        // When
        val viewModel = PersonalViewModel(apiService)

        // Then
        viewModel.uiState.test {
            assertEquals(PersonalUiState.Loading, awaitItem())
            testDispatcher.scheduler.advanceUntilIdle()
            val state = awaitItem()
            assert(state is PersonalUiState.Error)
            assertEquals(errorMessage, (state as PersonalUiState.Error).message)
        }
    }

    @Test
    fun `when loadData is called and api returns empty list then it should emit Error state`() = runTest {
        // Given
        coEvery { apiService.fetchPersonal() } returns emptyList()

        // When
        val viewModel = PersonalViewModel(apiService)

        // Then
        viewModel.uiState.test {
            assertEquals(PersonalUiState.Loading, awaitItem())
            testDispatcher.scheduler.advanceUntilIdle()
            val state = awaitItem()
            assert(state is PersonalUiState.Error)
            assertEquals("Dados não encontrados", (state as PersonalUiState.Error).message)
        }
    }
}
