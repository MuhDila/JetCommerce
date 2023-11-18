package com.muhdila.jetcommerce.data

import com.muhdila.jetcommerce.model.FakeItemsDataSource
import com.muhdila.jetcommerce.model.OrderItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ItemRepository {

    private val orderItems = mutableListOf<OrderItem>()

    init {
        if (orderItems.isEmpty()) {
            FakeItemsDataSource.dummyItems.forEach {
                orderItems.add(OrderItem(it, 0))
            }
        }
    }

    fun getAllItems(): Flow<List<OrderItem>> {
        return flowOf(orderItems)
    }

    fun getOrderItemsById(rewardId: Long): OrderItem {
        return orderItems.first {
            it.item.id == rewardId
        }
    }

    // New function to search for specific items based on a query
    fun searchItems(query: String): Flow<List<OrderItem>> {
        return getAllItems()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    // Add your search logic here, for example, searching by item title
                    orderReward.item.title.contains(query, ignoreCase = true)
                }
            }
    }

    fun updateOrderItem(rewardId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderItems.indexOfFirst { it.item.id == rewardId }
        val result = if (index >= 0) {
            val orderReward = orderItems[index]
            orderItems[index] =
                orderReward.copy(item = orderReward.item, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderItems(): Flow<List<OrderItem>> {
        return getAllItems()
            .map { orderRewards ->
                orderRewards.filter { orderReward ->
                    orderReward.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(): ItemRepository =
            instance ?: synchronized(this) {
                ItemRepository().apply {
                    instance = this
                }
            }
    }
}