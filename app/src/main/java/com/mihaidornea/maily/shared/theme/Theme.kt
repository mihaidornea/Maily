package com.mihaidornea.maily.shared.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.mihaidornea.maily.shared.theme.MailyColorPalette.DarkRed
import com.mihaidornea.maily.shared.theme.MailyColorPalette.Red
import com.mihaidornea.maily.shared.theme.MailyColorPalette.White

private val LightColorScheme = lightColorScheme(
    primary = Red,
    onPrimary = White,
    secondary = White,
    tertiary = DarkRed
)

@Composable
fun MailyTheme(
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicLightColorScheme(context)
        }

        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
