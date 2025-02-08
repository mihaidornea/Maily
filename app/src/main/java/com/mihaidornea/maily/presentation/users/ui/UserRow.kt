package com.mihaidornea.maily.presentation.users.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mihaidornea.maily.R
import com.mihaidornea.maily.presentation.users.model.UserUiModel
import com.mihaidornea.maily.shared.theme.MailyColorPalette
import com.mihaidornea.maily.shared.theme.MailyTypography
import com.mihaidornea.maily.shared.theme.Radius
import com.mihaidornea.maily.shared.theme.Size
import com.mihaidornea.maily.shared.theme.Space

const val TEST_PICTURE_URL =
    "https://scontent.fotp3-2.fna.fbcdn.net/v/t39.30808-1/224570164_4160641867363552_386189127362" +
        "4238752_n.jpg?stp=c583.0.1364.1364a_dst-jpg_s200x200_tt6&_nc_cat=106&ccb=1-7&_nc_sid" +
        "=e99d92&_nc_ohc=_kHMdQoALdMQ7kNvgGbuwRD&_nc_oc=AdglrzTjogWOX4GIFa5Pq_7vqX6OSEPHRTq_S" +
        "vlqb2k-pK6b8TAGC573vWAP4ZKTjwo&_nc_zt=24&_nc_ht=scontent.fotp3-2.fna&_nc_gid=ALnEFX5" +
        "mSwpD5BoTPBu2lHP&oh=00_AYBjd35mZy1yzKNt9SJ4576TQxIoVePlr-aBsEfNscQ21Q&oe=67AD7A6B"

@Composable
fun UserRow(
    userUiModel: UserUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(vertical = Space.Space_16)
        ) {
            Spacer(modifier = Modifier.width(Space.Space_16))
            AsyncImage(
                model = userUiModel.pictureUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .requiredSize(Size.UserImageSize)
                    .clip(RoundedCornerShape(Radius.Radius_32))
            )
            Spacer(modifier = Modifier.width(Space.Space_16))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = userUiModel.name,
                    style = MailyTypography.Title,
                    color = MailyColorPalette.DarkGray
                )
                Text(
                    text = stringResource(
                        R.string.user_age_and_location,
                        userUiModel.age,
                        userUiModel.nationality
                    ),
                    style = MailyTypography.Subtitle,
                    color = MailyColorPalette.Gray
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(end = Space.Space_16)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_attachement),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.LightGray)
                    )
                    Text(
                        text = userUiModel.registeredTime,
                        style = MailyTypography.LabelSmall,
                        color = MailyColorPalette.LightGray,
                        modifier = Modifier
                            .padding(start = Space.Space_4)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.ic_star),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.LightGray),
                    modifier = Modifier.padding(top = Space.Space_12)
                )
            }
        }
    }
}

@Preview
@Composable
private fun UserRowPreview() {
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
