package com.mihaidornea.maily.presentation.users

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mihaidornea.maily.R
import com.mihaidornea.maily.shared.composables.TopBar
import com.mihaidornea.maily.shared.theme.MailyColorPalette
import com.mihaidornea.maily.shared.theme.Size
import com.mihaidornea.maily.shared.theme.Space
import kotlinx.coroutines.launch

@Composable
fun UsersScreen() {
    val viewModel = hiltViewModel<UsersViewModel>()
    val uiState = viewModel.uiState.collectAsState().value
    val isLoading = viewModel.isLoading.collectAsState().value

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val fabBottomPadding by animateDpAsState(
        targetValue = if (snackbarHostState.currentSnackbarData != null) Space.Space_64 else Space.Space_0,
        animationSpec = tween(),
        label = "Fab spring animation when snackbar is displayed"
    )

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
        floatingActionButton = {
            FloatingButton(
                bottomPadding = fabBottomPadding
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                TopBar(
                    title = R.string.users_title,
                    startIcon = R.drawable.ic_menu,
                    endIcon = R.drawable.ic_search
                )
                UsersScreenContent(
                    uiState = uiState,
                    isLoading = isLoading,
                    onLoadMoreItems = { viewModel.loadNextUsers() },
                )
            }
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.Bottom)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
private fun FloatingButton(bottomPadding: Dp) {
    FloatingActionButton(
        shape = CircleShape,
        onClick = {},
        modifier = Modifier.padding(bottom = bottomPadding)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(MailyColorPalette.Red)
                .size(Size.FabSize)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = null,
            )
        }
    }
}
