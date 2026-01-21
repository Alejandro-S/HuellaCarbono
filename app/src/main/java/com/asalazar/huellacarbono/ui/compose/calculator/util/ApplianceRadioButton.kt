package com.asalazar.huellacarbono.ui.compose.calculator.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.compose.calculator.Appliance

@Composable
fun ApplianceRadioButton(
    appliance: Appliance,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(vertical = 4.dp)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Text(
            text = appliance.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ApplianceRadioButtonForm(
    items: List<Appliance>,
    modifier: Modifier = Modifier,
    onItemSelected: (Appliance) -> Unit
) {
    var selectedId by remember { mutableIntStateOf(-1) }
    Column(modifier = modifier) {
        items.forEach { appliance ->
            ApplianceRadioButton(
                appliance = appliance,
                isSelected = appliance.id == selectedId,
                onClick = {
                    selectedId = appliance.id
                    onItemSelected(appliance)
                }
            )
        }
    }
}
