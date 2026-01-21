package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceCheckboxForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.IndicationText
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun TransportModeSection(
    itemsSelectedChange: (List<Appliance>) -> Unit,
    enabledNextButton: Boolean,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    FormLayoutView(
        answerText = { AnswerText("4. ¿CÓMO TE TRANSPORTAS PARA IR AL TRABAJO O A LA ESCUELA?") },
        indicationText = { IndicationText("Selecciona todas las opciones que uses") },
        form = {
            ApplianceCheckboxForm(CarbonData.transportOptions, onValueChanged = itemsSelectedChange)
        },
        buttons = {
            NavigationController(
                enabledNextButton = enabledNextButton,
                onBack = onBack,
                onNext = onNext
            )
        }
    )
}

@Preview
@Composable
private fun TransportModeSectionPreviw() {
    HuellaCarbonoTheme {
        TransportModeSection(
            {},
            true,
            {},
            {}
        )
    }
}
