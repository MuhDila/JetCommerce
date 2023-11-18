package com.muhdila.jetcommerce.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.muhdila.jetcommerce.di.Injection
import com.muhdila.jetcommerce.model.FakeItemsDataSource
import com.muhdila.jetcommerce.model.OrderItem
import com.muhdila.jetcommerce.ui.ViewModelFactory
import com.muhdila.jetcommerce.ui.common.UiState
import com.muhdila.jetcommerce.ui.components.TopSearchBar
import com.muhdila.jetcommerce.ui.components.ClothesItem
import com.muhdila.jetcommerce.ui.components.TrendingItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
    navController: NavController
) {
    // State to track the search query
    var searchQuery by remember { mutableStateOf("") }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllItems()
            }

            is UiState.Success -> {
                HomeContent(
                    orderItem = uiState.data,
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { newQuery ->
                        searchQuery = newQuery
                        viewModel.searchItems(newQuery)
                    },
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    navController = navController
                )
            }

            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    orderItem: List<OrderItem>,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    navController: NavController
) {
    val categories = FakeItemsDataSource.dummyItems.map { it.category }.distinct()

    // Set to keep track of selected categories
    var selectedCategories by remember { mutableStateOf<Set<String>>(emptySet()) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { },
                scrollBehavior = scrollBehavior,
                actions = {
                    TopSearchBar(
                        query = searchQuery,
                        onQueryChange = onSearchQueryChanged,
                        navController = navController
                    )
                },
            )
        }
    ){ innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxSize() // Ensure LazyColumn takes the available space
                .testTag("Home_Screen")
        ) {
            item {
                TrendingItem()
            }
            item {
                // Row of horizontal scrollable buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(bottom = 16.dp)
                        .padding(top = 8.dp)
                ) {
                    // "All" button
                    Button(
                        onClick = {
                            // Show all items by setting selectedCategories to an empty set
                            selectedCategories = emptySet()
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedCategories.isEmpty()) MaterialTheme.colorScheme.outline else Color.LightGray
                        ),
                    ) {
                        Text(text = "All")
                    }

                    categories.forEach { category ->
                        // Determine whether the button should be filled or outlined
                        val isFilled = category in selectedCategories

                        Button(
                            onClick = {
                                // Handle button click to update the selected categories
                                selectedCategories = if (isFilled) {
                                    selectedCategories - category
                                } else {
                                    selectedCategories + category
                                }
                            },
                            modifier = Modifier.padding(end = 8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isFilled) MaterialTheme.colorScheme.outline else Color.LightGray
                            )
                        ) {
                            Text(text = category)
                        }
                    }
                }
            }

            // Filter and display items based on the selected categories
            val filteredItems =
                if (selectedCategories.isNotEmpty()) {
                    orderItem.filter { it.item.category in selectedCategories }
                } else {
                    orderItem
                }

            items(filteredItems.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    for (item in rowItems) {
                        ClothesItem(
                            image = item.item.image,
                            title = item.item.title,
                            requiredPoint = item.item.price,
                            modifier = modifier
                                .weight(1f)
                                .clickable {
                                    navigateToDetail(item.item.id)
                                }
                                .testTag("ClothesItem_${item.item.id}")
                        )
                    }
                }
            }
        }
    }
}