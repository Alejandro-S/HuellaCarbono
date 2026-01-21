/*
 * Proyecto: Calculadora de Huella de Carbono
 * Certificación: Android Developer Software - Tecnológico de Monterrey
 * Desarrollador: Alejandro Salazar
 * Contacto: salazaralejandro767@gmail.com
 *
 * Copyright (c) 2026 Alejandro Salazar
 * * Se otorga permiso, de forma gratuita, a cualquier persona que obtenga una copia
 * de este software y de los archivos de documentación asociados, para utilizar
 * el software sin restricción, incluyendo los derechos de uso, copia, modificación,
 * fusión, publicación y distribución.
 *
 * EL SOFTWARE SE PROPORCIONA "TAL CUAL", SIN GARANTÍA DE NINGÚN TIPO.
 * Licencia: MIT (https://opensource.org/licenses/MIT)
 */

package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun CalculatorScreen() {
    val innerNavController = rememberNavController()
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            LinearProgressIndicator(
                progress = { 0.5f }, //TODO: Add calculation to end developer
                modifier = Modifier.fillMaxWidth()
            )
            CalculatorInnerNavHost(
                innerNavController = innerNavController,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun CalculatorInnerNavHost(
    modifier: Modifier = Modifier,
    innerNavController: NavHostController = rememberNavController(),
    calculatorViewModel: CalculatorViewModel = CalculatorViewModel()
) {
    NavHost(
        navController = innerNavController,
        startDestination = "step1",
        modifier = modifier
    ) {
        composable(route = "step1") {
            ElectronicAppliancesSection(onTotalChanged = calculatorViewModel::updateElectronicScore) {
                innerNavController.navigate("step2")
            }
        }

        composable(route = "step2") {
            GasEmissionApplianceSection(
                onTotalChanged = calculatorViewModel::updateGasScore,
                enableNextStep = calculatorViewModel.gasStepReady,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("step3") }
            )
        }

        composable(route = "step3") {
            BulbEmissionApplianceSection(
                onTotalChanged = calculatorViewModel::updateBulbScore,
                enableNextStep = calculatorViewModel.bulbStepReady,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("step4") }
            )
        }

        composable(route = "step4") {
            TransportModeSection(
                itemsSelectedChange = calculatorViewModel::updateTransportModes,
                enabledNextButton = calculatorViewModel.transportModeReady,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("step5") }
            )
        }

        composable(route = "step5") {
            TransportTimeSection(
                onTotalChanged = calculatorViewModel::updateTransportTime,
                enableNextStep = calculatorViewModel.timeTransportReady,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("step6") }
            )
        }

        composable(route = "step6") {
            FlightEmissionSection(
                onApplianceSelected = calculatorViewModel::setFlightOption,
                enableNextButton = calculatorViewModel.isFlightStepReady,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("step7") }
            )
        }
    }
}

@Preview
@Composable
private fun CalculatorScreenPreview() {
    HuellaCarbonoTheme {
        CalculatorScreen()
    }
}
