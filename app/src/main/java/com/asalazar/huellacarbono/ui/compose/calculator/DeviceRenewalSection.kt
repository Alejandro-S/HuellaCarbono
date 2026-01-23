package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceRadioButtonForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme


@Composable
fun DeviceRenewalSection(
    setDeviceScore: (device: String, appliance: Appliance) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    isDeviceStepReady: Boolean
) {
    val devices = listOf("Celular", "Tableta", "Computadora", "Consola de videojuegos")

    FormLayoutView(
        answerText = { AnswerText("10. CADA CUANTO CAMBIO MIS DISPOSITIVOS ELECTRÓNICOS") },
        indicationText = {},
        form = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                devices.forEach { device ->
                    Column(modifier = Modifier.padding(vertical = 12.dp)) {
                        Text(
                            text = device,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        ApplianceRadioButtonForm(
                            items = CarbonMatrixData.getDeviceOptions(device)
                        ) { selected ->
                            setDeviceScore(device, selected)
                        }
                    }
                    HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
                }
            }
        },
        buttons = {
            NavigationController(
                enabledNextButton = isDeviceStepReady,
                onBack = onBack,
                onNext = onNext
            )
        }
    )
}

@Preview
@Composable
private fun DeviceRenewalSectionPreview() {
    HuellaCarbonoTheme {
        DeviceRenewalSection(
            { _, _ -> },
            {},
            {},
            true
        )
    }
}
