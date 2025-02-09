package com.mihaidornea.maily.presentation.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mihaidornea.maily.presentation.users.model.UserUiModel
import com.mihaidornea.maily.presentation.users.model.UsersScreenUiState
import com.mihaidornea.maily.presentation.users.ui.UserRow
import com.mihaidornea.maily.shared.theme.MailyColorPalette
import com.mihaidornea.maily.shared.theme.MailyTheme
import com.mihaidornea.maily.shared.theme.Size
import com.mihaidornea.maily.shared.theme.Space

private const val PAGINATION_TRIGGER_DELTA = 3

@Composable
fun UsersScreenContent(
    uiState: UsersScreenUiState,
    isLoading: Boolean,
    onLoadMoreItems: () -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()

    val lastVisibleItemIndex by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
    }

    LaunchedEffect(lastVisibleItemIndex) {
        lastVisibleItemIndex?.let { index ->
            if (index >= lazyListState.layoutInfo.totalItemsCount - PAGINATION_TRIGGER_DELTA)
                onLoadMoreItems()
        }
    }

    if (uiState.users.isEmpty() && isLoading) {
        LoadingScreen()
    }

    LazyColumn(
        state = lazyListState,
        modifier = modifier
            .fillMaxSize()
            .background(MailyColorPalette.OffWhite)
    ) {
        items(items = uiState.users) { userUiModel ->
            UserRow(
                userUiModel = userUiModel
            )
            HorizontalDivider(color = MailyColorPalette.LightGray)
        }
        if (isLoading) {
            item {
                LoadingIndicator()
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MailyColorPalette.OffWhite)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(Size.LargeLoadingIndicatorWidth),
            color = MailyColorPalette.DarkRed,
            trackColor = MailyColorPalette.LightRed,
        )
    }
}

@Composable
private fun LoadingIndicator() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Space.Space_16)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(Size.LoadingIndicatorWidth),
            color = MailyColorPalette.DarkRed,
            trackColor = MailyColorPalette.LightRed,
        )
    }
}

@Preview
@Composable
private fun UsersScreenPreview() {
    MailyTheme {
        UsersScreenContent(
            uiState = UsersScreenUiState(
                users = listOf(
                    UserUiModel(
                        name = "Mihai Dornea",
                        age = 28,
                        nationality = "RO",
                        registeredTime = "16:32",
                        pictureUrl = ""
                    )
                )
            ),
            onLoadMoreItems = {},
            isLoading = true
        )
    }
}
