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
fun FilledIconToggleButtonSample(
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
            colors = IconButtonDefaults.filledIconToggleButtonColors(Color.LightGray),
        ) {
            if (checked1) {
                Icon(
                    painterResource(R.drawable.ic_alpabet_s),
                    contentDescription = "Size S",
                    tint = Color.White
                )
            } else {
                Icon(
                    painterResource(R.drawable.ic_alpabet_s),
                    contentDescription = "Size S",
                    tint = Color.Black
                )
            }
        }

        FilledIconToggleButton(
            checked = checked2, onCheckedChange = { checked2 = it },
            colors = IconButtonDefaults.filledIconToggleButtonColors(Color.LightGray),
        ) {
            if (checked2) {
                Icon(
                    painterResource(R.drawable.ic_alpabet_m),
                    contentDescription = "Size M",
                    tint = Color.White
                )
            } else {
                Icon(
                    painterResource(R.drawable.ic_alpabet_m),
                    contentDescription = "Size M",
                    tint = Color.Black
                )
            }
        }

        FilledIconToggleButton(
            checked = checked3, onCheckedChange = { checked3 = it },
            colors = IconButtonDefaults.filledIconToggleButtonColors(Color.LightGray),
        ) {
            if (checked3) {
                Icon(
                    painterResource(R.drawable.ic_alpabet_l),
                    contentDescription = "Size L",
                    tint = Color.White
                )
            } else {
                Icon(
                    painterResource(R.drawable.ic_alpabet_l),
                    contentDescription = "Size L",
                    tint = Color.Black
                )
            }
        }
    }
}