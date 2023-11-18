package com.muhdila.jetcommerce.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.muhdila.jetcommerce.R

@Composable
fun ColorOptions(
    modifier: Modifier = Modifier
) {
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }
    var checked3 by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
    ) {
        FilledIconToggleButton(
            checked = checked1, onCheckedChange = { checked1 = it },
            colors = IconButtonDefaults.filledIconToggleButtonColors(Color.Red),
        ) {
            if (checked1) {
                Icon(
                    painterResource(R.drawable.ic_color_r),
                    contentDescription = "Color Red",
                    tint = Color.Red
                )
            } else {
                Icon(
                    painterResource(R.drawable.ic_color_r),
                    contentDescription = "Color Red",
                    tint = Color.Red
                )
            }
        }

        FilledIconToggleButton(
            checked = checked2, onCheckedChange = { checked2 = it },
            colors = IconButtonDefaults.filledIconToggleButtonColors(Color.Yellow),
        ) {
            if (checked2) {
                Icon(
                    painterResource(R.drawable.ic_color_y),
                    contentDescription = "Color Yellow",
                    tint = Color.Yellow
                )
            } else {
                Icon(
                    painterResource(R.drawable.ic_color_y),
                    contentDescription = "Color Yellow",
                    tint = Color.Yellow
                )
            }
        }

        FilledIconToggleButton(
            checked = checked3, onCheckedChange = { checked3 = it },
            colors = IconButtonDefaults.filledIconToggleButtonColors(Color.Green),
        ) {
            if (checked3) {
                Icon(
                    painterResource(R.drawable.ic_color_g),
                    contentDescription = "Color Green",
                    tint = Color.Green
                )
            } else {
                Icon(
                    painterResource(R.drawable.ic_color_g),
                    contentDescription = "Color Green",
                    tint = Color.Green
                )
            }
        }
    }
}