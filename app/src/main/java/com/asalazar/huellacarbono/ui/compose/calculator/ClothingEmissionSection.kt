package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.ApplianceRadioButtonForm
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun ClothingEmissionSection(
    setClothing: (Appliance) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    isClothingStepReady: Boolean
) {
    FormLayoutView(
        answerText = { AnswerText("9. LA ROPA QUE UTILIZO") },
        indicationText = {},
        form = {
            ApplianceRadioButtonForm(
                items = CarbonData.clothingOptions
            ) { selected ->
                setClothing(selected)
            }
        },
        buttons = {
            NavigationController(
                enabledNextButton = isClothingStepReady,
                onBack = onBack,
                onNext = onNext
            )
        }
    )
}

@Preview
@Composable
private fun ClothingEmissionSectionPreview() {
    HuellaCarbonoTheme {
        ClothingEmissionSection(
            {},
            {},
            {},
            true
        )
    }
}
