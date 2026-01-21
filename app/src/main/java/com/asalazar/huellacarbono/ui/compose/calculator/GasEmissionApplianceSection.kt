package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceRadioButtonForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun GasEmissionApplianceSection(
    onTotalChanged: (Double) -> Unit,
    enableNextStep: Boolean,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    FormLayoutView(
        answerText = { AnswerText("2. EN MI DOMICILIO UTILIZO:") },
        indicationText = {},
        form = {
            ApplianceRadioButtonForm(CarbonData.gasOptions) { selected ->
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

@Preview
@Composable
private fun GasEmisionApplianceSectionPreview() {
    HuellaCarbonoTheme {
        GasEmissionApplianceSection(
            onTotalChanged = {},
            onBack = {},
            onNext = {},
            enableNextStep = true
        )
    }
}
