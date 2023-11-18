package com.muhdila.jetcommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.muhdila.jetcommerce.R
import com.muhdila.jetcommerce.ui.navigation.Screen
import com.muhdila.jetcommerce.ui.theme.myFont2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController // Add the NavController parameter
) {
    ProvideTextStyle(
        value = TextStyle(
            fontFamily = myFont2, // Replace with your desired fontFamily
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp // Adjust the fontSize as needed
        )
    ) {
        Row(
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = {},
                active = false,
                onActiveChange = {},
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { /* Handle click */ },
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .size(40.dp)
                    ) {
                        val icon: Painter = painterResource(id = R.drawable.ic_microphone)
                        Icon(
                            painter = icon,
                            contentDescription = "Icon Microphone",
                            tint = Color.Black
                        )
                    }
                },
                placeholder = {
                    Text(
                        stringResource(R.string.search),
                        color = Color.Black,
                        fontSize = 15.sp,
                    )
                },
                colors = SearchBarDefaults.colors(
                    Color.LightGray,
                    inputFieldColors = SearchBarDefaults.inputFieldColors(Color.Black)
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .testTag("Search_Bar")
            ) {}

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = { navController.navigate(Screen.Cart.route) },
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .size(40.dp)
                    .testTag("Icon_Bag_Shopping")
            ) {
                val icon: Painter = painterResource(id = R.drawable.ic_shopping_bag)
                Icon(
                    painter = icon,
                    contentDescription = "Icon Bag Shopping",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                painter = painterResource(id = R.drawable.img_profile),
                contentDescription = "Image Profile",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.Bottom)
                    .clickable { navController.navigate(Screen.Profile.route) }
                    .testTag("Icon_Avatar")
            )
        }
    }
}