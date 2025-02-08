package com.mihaidornea.maily.presentation.users

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UsersScreen() {
    val viewModel = hiltViewModel<UsersViewModel>()
    val uiState = viewModel.uiState.collectAsState().value
    UsersScreenContent(uiState = uiState)
}
