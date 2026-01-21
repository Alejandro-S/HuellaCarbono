package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceCheckboxForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.IndicationText
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun ElectronicAppliancesSection(onTotalChanged: (Double) -> Unit) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AnswerText("1. ¿QUÉ APARATOS ELECTRÓNICOS TIENES EN CASA?")
            IndicationText("SELECCIONA TODOS LOS QUE TENGAS")
            ApplianceCheckboxForm(CarbonData.electronicAppliances) { checkeds ->
                onTotalChanged(checkeds.sumOf { it.emissions })
            }
        }
    }
}

@Preview
@Composable
private fun ElectronicAppliancesSectionPreview() {
    HuellaCarbonoTheme {
        ElectronicAppliancesSection {}
    }
}
