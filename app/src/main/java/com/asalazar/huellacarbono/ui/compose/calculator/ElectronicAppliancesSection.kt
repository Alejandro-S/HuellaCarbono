package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceCheckboxForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.IndicationText
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun ElectronicAppliancesSection(
    onTotalChanged: (Double) -> Unit,
    onNext: () -> Unit
) {
    FormLayoutView(
        answerText = { AnswerText("1. ¿QUÉ APARATOS ELECTRÓNICOS TIENES EN CASA?") },
        indicationText = { IndicationText("Selecciona todos los que tengas") },
        form = {
            ApplianceCheckboxForm(CarbonData.electronicAppliances) { checkeds ->
                onTotalChanged(checkeds.sumOf { it.emissions })
            }
        },
        buttons = {
            Button(onClick = onNext, modifier = Modifier.fillMaxWidth()) {
                Text("Siguiente")
            }
        }
    )
}

@Preview
@Composable
private fun ElectronicAppliancesSectionPreview() {
    HuellaCarbonoTheme {
        ElectronicAppliancesSection({}) {}
    }
}
