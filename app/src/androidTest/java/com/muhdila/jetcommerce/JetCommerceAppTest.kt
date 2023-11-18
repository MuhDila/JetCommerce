package com.muhdila.jetcommerce

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextInput
import com.muhdila.jetcommerce.ui.navigation.Screen
import org.junit.Rule
import org.junit.Test

class JetCommerceAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNavigation() {
        // Navigate to the cart
        composeTestRule.onNodeWithTag("${Screen.Cart.route}_Icon", useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithTag("Cart_Screen").assertIsDisplayed()

        // Navigate to the profile
        composeTestRule.onNodeWithTag("${Screen.Profile.route}_Icon", useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithTag("Profile_Screen").assertIsDisplayed()

        // Navigate to the home
        composeTestRule.onNodeWithTag("${Screen.Home.route}_Icon", useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithTag("Home_Screen").assertIsDisplayed()

        // Navigate to the cart
        composeTestRule.onNodeWithTag("Icon_Bag_Shopping", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("Cart_Screen").assertIsDisplayed()

        // Navigate to the home
        composeTestRule.onNodeWithTag("${Screen.Home.route}_Icon", useUnmergedTree = true)
            .performClick()
        composeTestRule.onNodeWithTag("Home_Screen").assertIsDisplayed()

        // Navigate to the profile
        composeTestRule.onNodeWithTag("Icon_Avatar", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("Profile_Screen").assertIsDisplayed()
    }

    @Test
    fun searchItem() {
        // Find item
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.search))
            .performTextInput("Hoodie Alrism")

        composeTestRule.onNodeWithTag("ClothesItem_20").assertIsDisplayed().performClick()
        composeTestRule.onNodeWithTag("Detail_Screen").assertIsDisplayed()

        // Check if the items is correct
        composeTestRule.onNodeWithText("Hoodie Alrism").assertIsDisplayed()
    }

    @Test
    fun mainTest() {
        // Test Scroll Down and Up
        composeTestRule.onNodeWithTag("Home_Screen")
            .performScrollToNode(hasTestTag("ClothesItem_19"))
        composeTestRule.onNodeWithTag("Home_Screen")
            .performScrollToNode(hasTestTag("ClothesItem_1"))

        // Category
        composeTestRule.onNodeWithText("Hoodie").performClick()
        composeTestRule.onNodeWithText("Shirt").performClick()
        composeTestRule.onNodeWithText("T-Shirt").performClick()

        // Click Item ID 20
        composeTestRule.onNodeWithTag("ClothesItem_1", useUnmergedTree = true).performClick()

        // Navigate to Detail
        composeTestRule.onNodeWithTag("Detail_Screen").assertIsDisplayed()

        // Click Count and Order
        composeTestRule.onNodeWithTag("Count", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("Order", useUnmergedTree = true).performClick()

        // Navigate to Cart
        composeTestRule.onNodeWithTag("Cart_Screen").assertIsDisplayed()

        // Test Remove Item
        composeTestRule.onNodeWithTag("Minus", useUnmergedTree = true).performClick()

        // Navigate to the home
        composeTestRule.onNodeWithTag("${Screen.Home.route}_Icon", useUnmergedTree = true).performClick()

        // Check if item ID 10 is back to 0
        composeTestRule.onNodeWithTag("ClothesItem_1", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("Total_Item").assertTextEquals("0")

        // Navigate to Cart
        composeTestRule.onNodeWithTag("Icon_Back", useUnmergedTree = true).performClick()

        composeTestRule.onNodeWithTag("Home_Screen").assertIsDisplayed()

        // Click Item ID 2
        composeTestRule.onNodeWithTag("ClothesItem_2", useUnmergedTree = true).performClick()

        // Click Count and Order
        composeTestRule.onNodeWithTag("Count", useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("Order", useUnmergedTree = true).performClick()

        // Click Order Button
        composeTestRule.onNodeWithTag("Total_Order", useUnmergedTree = true).performClick()
    }
}