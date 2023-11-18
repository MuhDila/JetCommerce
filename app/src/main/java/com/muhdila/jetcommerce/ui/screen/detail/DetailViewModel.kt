package com.muhdila.jetcommerce.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhdila.jetcommerce.data.ItemRepository
import com.muhdila.jetcommerce.model.OrderItem
import com.muhdila.jetcommerce.model.Items
import com.muhdila.jetcommerce.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderItem>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderItem>>
        get() = _uiState

    fun getItemById(itemsId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderItemsById(itemsId))
        }
    }

    fun addToCart(items: Items, count: Int) {
        viewModelScope.launch {
            repository.updateOrderItem(items.id, count)
        }
    }
}