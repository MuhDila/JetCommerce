package com.muhdila.jetcommerce.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muhdila.jetcommerce.R
import com.muhdila.jetcommerce.di.Injection
import com.muhdila.jetcommerce.ui.ViewModelFactory
import com.muhdila.jetcommerce.ui.common.UiState
import com.muhdila.jetcommerce.ui.components.ColorOptions
import com.muhdila.jetcommerce.ui.components.FilledIconToggleButtonSample
import com.muhdila.jetcommerce.ui.components.OrderButton
import com.muhdila.jetcommerce.ui.components.ProductCounter
import com.muhdila.jetcommerce.ui.theme.JetCommerceTheme

@Composable
fun DetailScreen(
    itemsId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getItemById(itemsId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.item.image,
                    data.item.title,
                    data.item.price,
                    data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.item, count)
                        navigateToCart()
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    basePoint: Int,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPoint by rememberSaveable { mutableIntStateOf(0) }
    var orderCount by rememberSaveable { mutableIntStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .testTag("Detail_Screen")
        ) {
            Box(
                modifier = Modifier.height(500.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = "Image Item",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(500.dp)
                        .fillMaxWidth()
                )
                IconButton(
                    modifier = Modifier.padding(16.dp),
                    onClick = { onBackClick() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_left),
                        contentDescription = "Icon Back",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.testTag("Icon_Back")
                    )
                }
                IconButton(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopEnd),
                    onClick = {  }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_favorite),
                        contentDescription = "Icon Favorite",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(MaterialTheme.colorScheme.outline)
                ) {
                    Text(
                        text = "Discount 75% Off",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 26.dp)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                            .background(MaterialTheme.colorScheme.background)
                            .align(Alignment.BottomStart) // Align the Spacer to the bottom of the Box
                    )
                }
            }
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = modifier.fillMaxWidth()
                        )
                        Text(
                            text = stringResource(R.string.required_point, basePoint),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            modifier = modifier.fillMaxWidth().padding(top = 8.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 26.dp, end = 16.dp, start = 16.dp)
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column{
                            Text(
                                text = "Color",
                                fontWeight = FontWeight.SemiBold,
                                modifier = modifier.padding(start = 5.dp)
                            )
                            ColorOptions(
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        }
                        Column {
                            Text(
                                text = "Size",
                                fontWeight = FontWeight.SemiBold,
                                modifier = modifier.padding(start = 5.dp)
                            )
                            FilledIconToggleButtonSample(
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 26.dp, end = 16.dp, start = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ProductCounter(
                            1,
                            orderCount,
                            onProductIncreased = { orderCount++ },
                        ) { if (orderCount > 0) orderCount-- }
                        totalPoint = basePoint * orderCount
                        OrderButton(
                            text = stringResource(R.string.add_cart),
                            enabled = orderCount > 0,
                            onClick = {
                                onAddToCart(orderCount)
                            },
                            modifier = Modifier.width(140.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    JetCommerceTheme {
        DetailContent(
            R.drawable.hoodie_alrism,
            "Hoodie Alrism",
            399000,
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DarkDetailContentPreview() {
    JetCommerceTheme(darkTheme = true) {
        DetailContent(
            R.drawable.hoodie_alrism,
            "Hoodie Alrism",
            399000,
            1,
            onBackClick = {},
            onAddToCart = {}
        )
    }
}