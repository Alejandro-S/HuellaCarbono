package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.compose.calculator.util.AnswerText
import com.asalazar.huellacarbono.ui.compose.calculator.util.FormLayoutView
import com.asalazar.huellacarbono.ui.compose.calculator.util.MatrixQUestionRow
import com.asalazar.huellacarbono.ui.compose.calculator.util.NavigationController
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun MeatConsumptionSection(
    setMeatScore: (type: String, appliance: Appliance) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    enabledNextButton: Boolean
) {
    val meatTypes = listOf("Res", "Cerdo", "Pollo", "Pescado")

    FormLayoutView(
        answerText = { AnswerText("7. ¿CON QUÉ FRECUENCIA COMES CARNE DE?") },
        indicationText = {},
        form = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())) {
                meatTypes.forEach { type ->
                    MatrixQUestionRow(
                        type = type,
                        options = CarbonMatrixData.getMeatOptions(type),
                        onOptionSelected = { appliance ->
                            setMeatScore(type, appliance)
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        thickness = 0.5.dp
                    )
                }
            }
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
private fun MeatConsumptionSectionPreview() {
    HuellaCarbonoTheme {
        MeatConsumptionSection({ _, _ ->

        }, {}, {}, true)
    }
}
