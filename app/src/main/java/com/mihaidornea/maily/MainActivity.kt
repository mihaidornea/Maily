package com.mihaidornea.maily

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.mihaidornea.maily.presentation.users.UsersScreen
import com.mihaidornea.maily.shared.theme.MailyColorPalette
import com.mihaidornea.maily.shared.theme.MailyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MailyTheme {
                // The reason for this box is purely esthetics, it was added so that the navigation
                // bar remains black while the status bar can have different colors
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MailyColorPalette.Black)
                ) {
                    UsersScreen()
                }
            }
        }
    }
}
