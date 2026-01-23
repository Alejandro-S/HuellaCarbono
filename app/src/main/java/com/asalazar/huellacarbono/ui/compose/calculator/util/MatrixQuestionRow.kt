package com.asalazar.huellacarbono.ui.compose.calculator.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.compose.calculator.Appliance

@Composable
fun MatrixQUestionRow(
    type: String,
    options: List<Appliance>,
    onOptionSelected: (Appliance) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MatrixQuestionTypeText(type)
        ApplianceRadioButtonForm(
            items = options,
            modifier = Modifier.padding(start = 8.dp)
        ) { selected ->
            onOptionSelected(selected)
        }
    }
}

@Composable
fun MatrixQuestionTypeText(type: String, modifier: Modifier = Modifier) {
    Text(
        text = type,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}
