package com.muhdila.jetcommerce.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhdila.jetcommerce.data.ItemRepository
import com.muhdila.jetcommerce.model.OrderItem
import com.muhdila.jetcommerce.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderItem>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderItem>>>
        get() = _uiState

    fun getAllItems() {
        viewModelScope.launch {
            repository.getAllItems()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderItems ->
                    _uiState.value = UiState.Success(orderItems)
                }
        }
    }

    fun searchItems(query: String) {
        viewModelScope.launch {
            repository.searchItems(query)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderItems ->
                    _uiState.value = UiState.Success(orderItems)
                }
        }
    }
}