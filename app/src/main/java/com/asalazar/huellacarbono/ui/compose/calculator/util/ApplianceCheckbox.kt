package com.asalazar.huellacarbono.ui.compose.calculator.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.compose.calculator.Appliance

@Composable
fun ApplianceCheckbox(
    appliance: Appliance,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (checked: Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { onCheckedChange(!isChecked) }
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = null,
        )
        Text(
            text = appliance.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun ApplianceCheckboxForm(
    items: List<Appliance>,
    modifier: Modifier = Modifier,
    onValueChanged: (checkeds: List<Appliance>) -> Unit
) {
    val selectedItems = remember { mutableStateMapOf<Int, Boolean>() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(
            items,
            key = { appliance -> appliance.id }) { appliance ->
            val isChecked = selectedItems[appliance.id] ?: false
            ApplianceCheckbox(appliance, isChecked) { checked ->
                selectedItems[appliance.id] = checked
                onValueChanged(items.filter { selectedItems[it.id] == true })
            }
        }
    }
}
