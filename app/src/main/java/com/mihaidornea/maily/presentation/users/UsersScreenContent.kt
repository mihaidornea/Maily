package com.mihaidornea.maily.presentation.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mihaidornea.maily.presentation.users.model.UserUiModel
import com.mihaidornea.maily.presentation.users.model.UsersScreenUiState
import com.mihaidornea.maily.presentation.users.ui.TEST_PICTURE_URL
import com.mihaidornea.maily.presentation.users.ui.UserRow
import com.mihaidornea.maily.shared.theme.MailyColorPalette
import com.mihaidornea.maily.shared.theme.MailyTheme

private const val PAGINATION_TRIGGER_DELTA = 3

@Composable
fun UsersScreenContent(
    uiState: UsersScreenUiState,
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
                        pictureUrl = TEST_PICTURE_URL
                    )
                )
            ),
            onLoadMoreItems = {}
        )
    }
}
