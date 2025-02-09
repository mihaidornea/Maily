package com.mihaidornea.maily.shared.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mihaidornea.maily.R
import com.mihaidornea.maily.shared.theme.MailyColorPalette
import com.mihaidornea.maily.shared.theme.MailyTheme
import com.mihaidornea.maily.shared.theme.MailyTypography
import com.mihaidornea.maily.shared.theme.Size
import com.mihaidornea.maily.shared.theme.Space

// Decided to use a custom TopAppBar instead of the scaffold TopAppBar in order to be able to have a
// slightly different colored status bar, like in the example. Using the scaffold version would
// apply the same color to the status bar as the top app bar.

@Composable
fun TopBar(
    @StringRes title: Int,
    @DrawableRes startIcon: Int,
    @DrawableRes endIcon: Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(Size.TopBarHeight)
            .fillMaxWidth()
            .background(MailyColorPalette.Red)
    ) {
        Image(
            painter = painterResource(startIcon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MailyColorPalette.White),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(Size.TopBarIconSize)
                .padding(horizontal = Space.Space_16)
        )
        Text(
            text = stringResource(title),
            style = MailyTypography.H1,
            color = MailyColorPalette.White,
            modifier = Modifier
                .height(Size.TopBarIconSize)
                .padding(horizontal = Space.Space_8)
                .weight(1f)
        )
        Image(
            painter = painterResource(endIcon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MailyColorPalette.White),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(Size.TopBarIconSize)
                .padding(horizontal = Space.Space_16)
        )
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    MailyTheme {
        TopBar(
            title = R.string.users_title,
            startIcon = R.drawable.ic_menu,
            endIcon = R.drawable.ic_search
        )
    }
}
