package com.muhdila.jetcommerce.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhdila.jetcommerce.data.ItemRepository
import com.muhdila.jetcommerce.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderItems() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderItems()
                .collect { orderItems ->
                    val totalRequiredPoint =
                        orderItems.sumOf { it.item.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderItems, totalRequiredPoint))
                }
        }
    }

    fun updateOrderItems(itemsId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderItem(itemsId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderItems()
                    }
                }
        }
    }
}