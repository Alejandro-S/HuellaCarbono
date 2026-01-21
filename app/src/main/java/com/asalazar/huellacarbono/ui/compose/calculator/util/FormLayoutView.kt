package com.asalazar.huellacarbono.ui.compose.calculator.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormLayoutView(
    answerText: @Composable () -> Unit,
    indicationText: @Composable () -> Unit,
    form: @Composable () -> Unit,
    buttons: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        answerText()
        indicationText()
        form()
        Spacer(Modifier.weight(1f))
        buttons()
    }
}
