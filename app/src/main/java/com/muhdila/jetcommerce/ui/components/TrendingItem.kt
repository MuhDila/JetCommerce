package com.muhdila.jetcommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muhdila.jetcommerce.R
import com.muhdila.jetcommerce.ui.theme.Shapes

@Composable
fun TrendingItem(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(top = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.trending),
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Image(
            painter = painterResource(R.drawable.trending_3),
            contentDescription = "Image Trending",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(Shapes.extraLarge)
                .fillMaxWidth()
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}