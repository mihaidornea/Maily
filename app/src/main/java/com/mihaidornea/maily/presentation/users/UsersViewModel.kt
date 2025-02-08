package com.mihaidornea.maily.presentation.users

import androidx.lifecycle.ViewModel
import com.mihaidornea.maily.presentation.users.model.UsersScreenUiState
import com.mihaidornea.maily.useCases.GetRandomUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getRandomUsersUseCase: GetRandomUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        UsersScreenUiState(
            users = listOf()
        )
    )
    val uiState: StateFlow<UsersScreenUiState> = _uiState
}
