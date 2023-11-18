package com.muhdila.jetcommerce.ui.screen.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.muhdila.jetcommerce.di.Injection
import com.muhdila.jetcommerce.ui.ViewModelFactory
import com.muhdila.jetcommerce.ui.common.UiState
import com.muhdila.jetcommerce.ui.components.CartItem
import com.muhdila.jetcommerce.ui.components.OrderButton
import com.muhdila.jetcommerce.R
import com.muhdila.jetcommerce.ui.theme.myFont2

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderItems()
            }
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    onProductCountChanged = { itemsId, count ->
                        viewModel.updateOrderItems(itemsId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shareMessage = stringResource(
        R.string.share_message,
        state.orderItem.count(),
        state.totalRequiredPrice
    )
    Column(
        modifier = modifier.fillMaxSize().testTag("Cart_Screen")
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = myFont2,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                )
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(weight = 1f)
        ) {
            items(state.orderItem, key = { it.item.id }) { item ->
                CartItem(
                    itemId = item.item.id,
                    image = item.item.image,
                    title = item.item.title,
                    totalPoint = item.item.price * item.count,
                    count = item.count,
                    onProductCountChanged = onProductCountChanged,
                )
            }
        }
        OrderButton(
            text = stringResource(R.string.total_order, state.totalRequiredPrice),
            enabled = state.orderItem.isNotEmpty(),
            onClick = {
                onOrderButtonClicked(shareMessage)
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .testTag("Total_Order")
        )
    }
}