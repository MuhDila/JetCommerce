package com.muhdila.jetcommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muhdila.jetcommerce.R
import com.muhdila.jetcommerce.ui.theme.JetCommerceTheme
import com.muhdila.jetcommerce.ui.theme.Shapes

@Composable
fun ClothesItem(
    image: Int,
    title: String,
    requiredPoint: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Item Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(180.dp)
                .clip(Shapes.extraLarge)
                .align(Alignment.CenterHorizontally)
                .background(Color.LightGray)
        )
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 8.dp, start = 5.dp, end = 5.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(R.string.required_point, requiredPoint),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 5.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RewardItemPreview() {
    JetCommerceTheme {
        ClothesItem(R.drawable.hoodie_alrism, "Hoodie Alrism", 299000)
    }
}