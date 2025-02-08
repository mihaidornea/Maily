package com.mihaidornea.maily.presentation.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mihaidornea.maily.shared.theme.MailyTheme

@Composable
fun UsersScreen() {
    val viewModel = hiltViewModel<UsersViewModel>()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Testing Testing"
            )
        }
    }
}

@Preview
@Composable
private fun UsersScreenPreview() {
    MailyTheme {
        UsersScreen()
    }
}
