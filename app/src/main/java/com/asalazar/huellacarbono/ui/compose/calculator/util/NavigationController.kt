package com.asalazar.huellacarbono.ui.compose.calculator.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun NavigationController(
    enabledNextButton: Boolean,
    onBack: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedButton(onClick = onBack, modifier = Modifier.weight(1f)) {
            Text("Atrás")
        }
        Button(onClick = onNext, enabled = enabledNextButton, modifier = Modifier.weight(1f)) {
            Text("Siguiente")
        }
    }
}

@Preview
@Composable
private fun NavigationControllerPreview() {
    HuellaCarbonoTheme {
        NavigationController(
            enabledNextButton = true,
            {},
            {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
