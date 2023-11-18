package com.muhdila.jetcommerce.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.muhdila.jetcommerce.ui.screen.detail.DetailViewModel
import com.muhdila.jetcommerce.ui.screen.home.HomeViewModel
import com.muhdila.jetcommerce.data.ItemRepository
import com.muhdila.jetcommerce.ui.screen.cart.CartViewModel

class ViewModelFactory(private val repository: ItemRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}