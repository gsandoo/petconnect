package com.haneum.petconnect.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.haneum.petconnect.ui.theme.shapes

@Composable
fun <T> Spinner(
    modifier: Modifier = Modifier,
    dropDownModifier: Modifier = Modifier,
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    selectedItemFactory: @Composable (Modifier, T, Boolean) -> Unit,
    dropdownItemFactory: @Composable (T, Int) -> Unit,
) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    var isSelected: Boolean by remember { mutableStateOf(false) }

    AppTheme() {
        Surface(
            color = Color.White,
            border = BorderStroke(1.dp, Color.LightGray),
            shape = shapes.medium,
            modifier = modifier.wrapContentSize(Alignment.TopStart)
        ) {
            selectedItemFactory(
                Modifier
                    .clickable { expanded = true },
                selectedItem,
                isSelected
            )

            androidx.compose.material3.DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = dropDownModifier.background(color = Color.White)
            ) {
                items.forEachIndexed { index, element ->
                    DropdownMenuItem(
                        text = {Text(text = element.toString())}
                        ,onClick = {
                            onItemSelected(items[index])
                            expanded = false
                            isSelected = true
                        })
                }
            }
        }
    }
}