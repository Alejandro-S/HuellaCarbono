package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.runtime.Composable
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceRadioButtonForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController

@Composable
fun TransportTimeSection(
    onTotalChanged: (Double) -> Unit,
    enableNextStep: Boolean,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    FormLayoutView(
        answerText = { AnswerText("5. ¿EN CUÁNTO TIEMPO HACES ESE RECORRIDO?") },
        indicationText = {},
        form = {
            ApplianceRadioButtonForm(CarbonData.timeMultipliers) { selected ->
                onTotalChanged(selected.emissions)
            }
        },
        buttons = {
            NavigationController(
                enabledNextButton = enableNextStep,
                onBack = onBack,
                onNext = onNext
            )
        }
    )
}
