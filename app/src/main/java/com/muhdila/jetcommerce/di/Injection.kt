package com.muhdila.jetcommerce.di

import com.muhdila.jetcommerce.data.ItemRepository

object Injection {
    fun provideRepository(): ItemRepository {
        return ItemRepository.getInstance()
    }
}