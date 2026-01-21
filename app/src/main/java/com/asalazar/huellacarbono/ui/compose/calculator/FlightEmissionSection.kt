package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.runtime.Composable
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceRadioButtonForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController

@Composable
fun FlightEmissionSection(
    onApplianceSelected: (Appliance) -> Unit,
    enableNextButton: Boolean,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    FormLayoutView(
        answerText = { AnswerText("6. ¿CON QUÉ FRECUENCIA VUELAS EN AVIÓN?") },
        indicationText = {},
        form = {
            ApplianceRadioButtonForm(CarbonData.flightOptions, onItemSelected = onApplianceSelected)
        },
        buttons = {
            NavigationController(
                enabledNextButton = enableNextButton,
                onBack = onBack,
                onNext = onNext
            )
        }
    )
}
