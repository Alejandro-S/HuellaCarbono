package com.asalazar.huellacarbono.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.R
import com.asalazar.huellacarbono.ui.compose.util.MainTitleText
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

private val componentLabels = arrayOf("Transporte", "Energía", "Consumo")

@Composable
fun ComponentsDescription(modifier: Modifier = Modifier) {
    Column(modifier) {
        MainTitleText(stringResource(R.string.title_components_description))
        Text(
            text = stringResource(R.string.label_components_description_body),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            componentLabels.forEachIndexed { index, label ->
                key(index) { InfoChip(label) }
            }
        }
    }
}

@Composable
fun InfoChip(text: String) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.padding(end = 4.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Preview
@Composable
private fun ComponentsDescriptionPreview() {
    HuellaCarbonoTheme {
        ComponentsDescription()
    }
}
