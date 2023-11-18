package com.muhdila.jetcommerce.ui.screen.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.muhdila.jetcommerce.R
import com.muhdila.jetcommerce.ui.theme.JetCommerceTheme
import com.muhdila.jetcommerce.ui.theme.myFont2

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val intentLinkedIn = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/muhammaddila/"))
    val intentGithub = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MuhDila"))
    val context = LocalContext.current

    Box(
        modifier = modifier.fillMaxSize().testTag("Profile_Screen"),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Image(
                painter = painterResource(R.drawable.img_profile),
                contentDescription = "about_page",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.my_name),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontFamily = myFont2
                ),
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
            )
            Text(
                text = stringResource(R.string.my_email),
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = myFont2
                ),
                modifier = Modifier
                    .width(250.dp)
                    .padding(top = 4.dp)
            )
            Row (
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                FilledIconButton(
                    onClick = { startActivity(context, intentLinkedIn, null) },
                    colors = IconButtonDefaults.filledIconButtonColors(Color.LightGray)
                ) {
                    Icon(
                        painterResource(R.drawable.ic_linkedin),
                        contentDescription = "Linked In",
                        tint = Color.Black,
                        modifier = Modifier
                            .testTag("count")
                    )
                }
                FilledIconButton(
                    onClick = { startActivity(context, intentGithub, null) },
                    colors = IconButtonDefaults.filledIconButtonColors(Color.LightGray)
                ) {
                    Icon(
                        painterResource(R.drawable.ic_github),
                        contentDescription = "Github",
                        tint = Color.Black,
                        modifier = Modifier
                            .testTag("count")
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun Preview() {
    JetCommerceTheme {
        ProfileScreen()
    }
}