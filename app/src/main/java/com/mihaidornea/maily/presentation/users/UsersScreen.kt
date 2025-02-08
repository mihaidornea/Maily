package com.mihaidornea.maily.presentation.users

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mihaidornea.maily.R
import com.mihaidornea.maily.shared.theme.MailyColorPalette
import kotlinx.coroutines.launch

@Composable
fun UsersScreen() {
    val viewModel = hiltViewModel<UsersViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is UsersViewModelEvents.ShowErrorSnackbar -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            context.getString(R.string.error_message)
                        )
                    }
                }

                else -> {}
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadNextUsers()
    }

    Scaffold(
        containerColor = MailyColorPalette.DarkRed,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
        },
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) { innerPadding ->
        UsersScreenContent(
            uiState = uiState,
            onLoadMoreItems = { viewModel.loadNextUsers() },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
