package com.mihaidornea.maily.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mihaidornea.maily.presentation.users.mapper.UserToUserUiModelMapper
import com.mihaidornea.maily.presentation.users.model.UsersScreenUiState
import com.mihaidornea.maily.useCases.GetRandomUsersUseCase
import com.mihaidornea.maily.utils.Paginator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_PAGE = 0
private const val MAX_PAGES = 2

sealed class UsersViewModelEvents {
    object ShowErrorSnackbar : UsersViewModelEvents()
}

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getRandomUsersUseCase: GetRandomUsersUseCase,
    private val userUiMapper: UserToUserUiModelMapper
) : ViewModel() {

    private val _currentPageNumber = MutableStateFlow(0)

    private val _uiState = MutableStateFlow(
        UsersScreenUiState(
            users = listOf()
        )
    )
    val uiState: StateFlow<UsersScreenUiState> = _uiState

    private val _events = MutableStateFlow<UsersViewModelEvents?>(null)
    val events: StateFlow<UsersViewModelEvents?> = _events

    private val usersPaginator = Paginator(
        initialKey = _currentPageNumber.value,
        onRequest = { key ->
            getRandomUsersUseCase(key)
        },
        onNextKey = { _currentPageNumber.value + 1 },
        onError = ::onError,
        onSuccess = { users, key ->
            onError()
            _uiState.update { currentState ->
                currentState.copy(
                    users = currentState.users + users.map { userUiMapper.mapUserToUserUiModel(it) }
                )
            }
            _currentPageNumber.update { key }
        },
        onReset = { _currentPageNumber.update { DEFAULT_PAGE } }
    )

    fun loadNextUsers(hasToReset: Boolean = false) {
        if (_currentPageNumber.value > MAX_PAGES)
            return
        viewModelScope.launch {
            usersPaginator.loadNextItems(hasToReset)
        }
    }

    private fun onError() {
        viewModelScope.launch {
            _events.emit(UsersViewModelEvents.ShowErrorSnackbar)
        }
    }
}
