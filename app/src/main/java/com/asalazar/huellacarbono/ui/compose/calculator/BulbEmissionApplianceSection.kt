package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.runtime.Composable
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceRadioButtonForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController

@Composable
fun BulbEmissionApplianceSection(
    onTotalChanged: (Double) -> Unit,
    enableNextStep: Boolean,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    FormLayoutView(
        answerText = { AnswerText("3. EN MI CASA LOS FOCOS SON:") },
        indicationText = { },
        form = {
            ApplianceRadioButtonForm(CarbonData.bulbOptions) { selected ->
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
