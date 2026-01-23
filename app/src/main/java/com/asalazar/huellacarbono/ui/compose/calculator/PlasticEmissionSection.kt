package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceRadioButtonForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun PlasticEmissionSection(
    setPlastic: (Appliance) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    isPlasticStepReady: Boolean,
) {
    FormLayoutView(
        answerText = { AnswerText("8. ¿CON QUÉ REGULARIDAD UTILIZAS PLÁSTICOS DE UN SOLO USO?") },
        indicationText = {},
        form = {
            ApplianceRadioButtonForm(
                items = CarbonData.plasticOptions
            ) { selected ->
                setPlastic(selected)
            }
        },
        buttons = {
            NavigationController(
                enabledNextButton = isPlasticStepReady,
                onBack = onBack,
                onNext = onNext
            )
        }
    )
}

@Preview
@Composable
private fun PlasticEmissionSectionPreview() {
    HuellaCarbonoTheme {
        PlasticEmissionSection({}, {}, {}, isPlasticStepReady = true)
    }
}
