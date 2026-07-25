package com.example.curriculum.ui.personal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.curriculum.data.model.Personal
import com.example.curriculum.di.NetworkModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PersonalUiState {
    object Loading : PersonalUiState()
    data class Success(val data: Personal) : PersonalUiState()
    data class Error(val message: String) : PersonalUiState()
}

class PersonalViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<PersonalUiState>(PersonalUiState.Loading)
    val uiState: StateFlow<PersonalUiState> = _uiState

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = PersonalUiState.Loading
            try {
                val personals = NetworkModule.apiService.fetchPersonal()
                if (personals.isNotEmpty()) {
                    _uiState.value = PersonalUiState.Success(personals.first())
                } else {
                    _uiState.value = PersonalUiState.Error("Dados não encontrados")
                }
            } catch (e: Exception) {
                _uiState.value = PersonalUiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}
