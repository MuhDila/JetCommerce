package com.muhdila.jetcommerce.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun CartItem(
    itemId: Long,
    image: Int,
    title: String,
    totalPoint: Int,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "Item Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.extraLarge)
                .background(Color.LightGray)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = stringResource(
                    R.string.required_point,
                    totalPoint
                ),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )
        }
        ProductCounter(
            orderId = itemId,
            orderCount = count,
            onProductIncreased = { onProductCountChanged(itemId, count + 1) }
        ) { onProductCountChanged(itemId, count - 1) }
    }
}

@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    JetCommerceTheme {
        CartItem(
            4, R.drawable.hoodie_alrism, "Hoodie Alrism", 299000, 0,
            onProductCountChanged = { _, _ -> },
        )
    }
}