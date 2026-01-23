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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun CalculatorScreen() {
    val innerNavController = rememberNavController()
    val navBackStackEntry by innerNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "step1"
    val targetProgress = getProgressForRoute(currentRoute)

    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(durationMillis = 500),
        label = "ProgressBarAnimation"
    )

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {

            LinearProgressIndicator(
                progress = { animatedProgress },
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF3AAE2A),
                trackColor = Color(0xFFE0E0E0)
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

        composable(route = "step7") {
            MeatConsumptionSection(
                calculatorViewModel::setMeatScore,
                enabledNextButton = calculatorViewModel.isMeatSectionReady,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("step8") })
        }

        composable(route = "step8") {
            PlasticEmissionSection(
                setPlastic = calculatorViewModel::setPlastic,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("step9") },
                isPlasticStepReady = calculatorViewModel.isPlasticStepReady
            )
        }
        composable(route = "step9") {
            ClothingEmissionSection(
                setClothing = calculatorViewModel::setClothing,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("step10") },
                isClothingStepReady = calculatorViewModel.isClothingStepReady
            )
        }

        composable(route = "step10") {
            DeviceRenewalSection(
                setDeviceScore = calculatorViewModel::setDeviceScore,
                onBack = { innerNavController.navigateUp() },
                onNext = { innerNavController.navigate("result") },
                isDeviceStepReady = calculatorViewModel.isDeviceStepReady
            )
        }

        composable("result") {
            val sumHome = calculatorViewModel.sumHome
            val sumTransport = calculatorViewModel.sumTransport
            val sumConsumption = calculatorViewModel.sumConsumption

            ResultsSection(
                result = calculatorViewModel.getResultData(),
                sumHome = sumHome,
                colorHome = calculatorViewModel.getColorForCategory(sumHome, 854.0),
                sumTransport = sumTransport,
                colorTransport = calculatorViewModel.getColorForCategory(sumTransport, 4080.0),
                sumConsumption = sumConsumption,
                colorConsumption = calculatorViewModel.getColorForCategory(sumConsumption, 2249.0)
            ) {
                calculatorViewModel.resetCalculator()
                innerNavController.navigate("step1") {
                    popUpTo(innerNavController.graph.id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }
    }
}

private fun getProgressForRoute(route: String): Float {
    return when (route) {
        "step1" -> 0.1f
        "step2" -> 0.2f
        "step3" -> 0.3f
        "step4" -> 0.4f
        "step5" -> 0.5f
        "step6" -> 0.6f
        "step7" -> 0.7f
        "step8" -> 0.8f
        "step9" -> 0.9f
        "step10" -> 1.0f
        "result" -> 1.0f
        else -> 0.0f
    }
}

@Preview
@Composable
private fun CalculatorScreenPreview() {
    HuellaCarbonoTheme {
        CalculatorScreen()
    }
}
