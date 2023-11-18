package com.muhdila.jetcommerce.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muhdila.jetcommerce.R
import com.muhdila.jetcommerce.ui.theme.JetCommerceTheme

@Composable
fun ProductCounter(
    orderId: Long,
    orderCount: Int,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit
) {
    Row{
        FilledIconButton(
            onClick = { onProductDecreased(orderId) },
            colors = IconButtonDefaults.filledIconButtonColors(Color.LightGray)
        ) {
            Icon(
                painterResource(R.drawable.ic_minus),
                contentDescription = "Icon Minus",
                tint = Color.Black,
                modifier = Modifier
                    .testTag("Minus")
            )
        }
        Text(
            text = orderCount.toString(),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .width(30.dp)
                .align(Alignment.CenterVertically)
                .testTag("Total_Item")
        )
        FilledIconButton(
            onClick = { onProductIncreased(orderId) },
            colors = IconButtonDefaults.filledIconButtonColors(Color.LightGray)
        ) {
            Icon(
                painterResource(R.drawable.ic_add),
                contentDescription = "Icon Plus",
                tint = Color.Black,
                modifier = Modifier
                    .testTag("Count")
            )
        }
    }
}

@Preview
@Composable
fun ProductCounterPreview() {
    JetCommerceTheme {
        ProductCounter(
            orderId = 1,
            orderCount = 0,
            onProductIncreased = { }
        ) { }
    }
}
