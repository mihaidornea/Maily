package com.mihaidornea.maily.presentation.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mihaidornea.maily.presentation.users.model.UserUiModel
import com.mihaidornea.maily.presentation.users.model.UsersScreenUiState
import com.mihaidornea.maily.presentation.users.ui.TEST_PICTURE_URL
import com.mihaidornea.maily.presentation.users.ui.UserRow
import com.mihaidornea.maily.shared.theme.MailyColorPalette
import com.mihaidornea.maily.shared.theme.MailyTheme

@Composable
fun UsersScreenContent(
    uiState: UsersScreenUiState
) {
    Scaffold(
        containerColor = MailyColorPalette.DarkRed,
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MailyColorPalette.OffWhite)
        ) {
            UserRow(
                userUiModel = UserUiModel(
                    name = "Mihai Dornea",
                    age = 28,
                    nationality = "RO",
                    registeredTime = "16:32",
                    pictureUrl = TEST_PICTURE_URL
                )
            )
        }
    }
}

@Preview
@Composable
private fun UsersScreenPreview() {
    MailyTheme {
        UsersScreenContent(
            uiState = UsersScreenUiState(
                users = listOf()
            )
        )
    }
}
