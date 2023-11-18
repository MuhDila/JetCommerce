package com.muhdila.jetcommerce.ui.screen.cart

import com.muhdila.jetcommerce.model.OrderItem

data class CartState(
    val orderItem: List<OrderItem>,
    val totalRequiredPrice: Int
)