package com.asalazar.huellacarbono.ui.compose.calculator

/**
 * NOTA LEGAL Y CRÉDITOS:
 * Este archivo contiene lógica de cálculo, factores de emisión (kg CO2e) y rangos
 * de evaluación obtenidos mediante ingeniería inversa del código fuente público
 * de la calculadora de huella de carbono de Greenpeace México.
 * * Autor original de la metodología: Greenpeace México.
 * Fuente: https://consumoresponsable.greenpeace.org.mx/calcula-tu-huella-de-carbono
 * * Este código ha sido adaptado a Kotlin/Jetpack Compose con fines estrictamente
 * educativos para la certificación del Tecnológico de Monterrey.
 */

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private var electronicScore by mutableDoubleStateOf(0.0)
    private var gasScore by mutableDoubleStateOf(0.0)
    private var bulbScore by mutableDoubleStateOf(0.0)
    private var selectedTransportEmissions = mutableStateListOf<Double>()
    private var timeTransport by mutableDoubleStateOf(0.0)
    private var flightScore by mutableDoubleStateOf(0.0)
    private val meatSelections = mutableStateMapOf<String, Int>()
    private val meatScores = mutableStateMapOf<String, Double>()
    private var plasticScore by mutableDoubleStateOf(0.0)
    private var clothingScore by mutableDoubleStateOf(0.0)
    private val deviceSelections = mutableStateMapOf<String, Int>()
    private val deviceScores = mutableStateMapOf<String, Double>()

    val transportTotalScore: Double
        get() {
            if (selectedTransportEmissions.isEmpty()) {
                return 0.0
            }
            val averageEmissions = selectedTransportEmissions.average()
            return averageEmissions * timeTransport
        }


    val gasStepReady
        get() = gasScore > 0
    val bulbStepReady: Boolean
        get() = bulbScore > 0
    val transportModeReady: Boolean
        get() = selectedTransportEmissions.isNotEmpty()
    val timeTransportReady: Boolean
        get() = timeTransport > 0
    var isFlightStepReady by mutableStateOf(false)
        private set
    val isMeatSectionReady: Boolean
        get() = meatSelections.size == 4
    val totalMeatScore: Double
        get() = meatScores.values.sum()
    var isPlasticStepReady by mutableStateOf(false)
        private set
    var isClothingStepReady by mutableStateOf(false)
        private set
    val isDeviceStepReady: Boolean get() = deviceSelections.size == 4

    fun setDeviceScore(device: String, appliance: Appliance) {
        deviceSelections[device] = appliance.id
        deviceScores[device] = appliance.emissions
    }

    val totalDeviceScore: Double get() = deviceScores.values.sum()

    fun setClothing(appliance: Appliance) {
        clothingScore = appliance.emissions
        isClothingStepReady = true
    }

    fun setMeatScore(type: String, appliance: Appliance) {
        meatSelections[type] = appliance.id
        meatScores[type] = appliance.emissions
    }

    fun setPlastic(appliance: Appliance) {
        plasticScore = appliance.emissions
        isPlasticStepReady = true
    }

    fun updateElectronicScore(score: Double) {
        electronicScore = score
    }

    fun updateGasScore(score: Double) {
        gasScore = score
    }

    fun updateBulbScore(score: Double) {
        bulbScore = score
    }

    fun updateTransportModes(selected: List<Appliance>) {
        selectedTransportEmissions.clear()
        selectedTransportEmissions.addAll(selected.map { it.emissions })
    }

    fun updateTransportTime(score: Double) {
        timeTransport = score
    }

    fun setFlightOption(appliance: Appliance) {
        flightScore = appliance.emissions
        isFlightStepReady = true
    }

    val sumHome: Double get() = electronicScore + gasScore + bulbScore
    val sumTransport: Double get() = transportTotalScore + flightScore
    val sumConsumption: Double get() = totalMeatScore + plasticScore + clothingScore + totalDeviceScore

    val totalScore: Double get() = sumHome + sumTransport + sumConsumption

    fun getColorForCategory(score: Double, max: Double): Color {
        val percentage = (score * 100) / max
        return when {
            percentage <= 25 -> Color(0xFF005100) // Verde
            percentage <= 50 -> Color(0xFFE5B602) // Amarillo
            percentage <= 75 -> Color(0xFFD36709) // Naranja
            else -> Color(0xFFC4250F)             // Rojo
        }
    }

    fun getResultData(): ResultInfo {
        val score = totalScore
        return when {
            score <= 2000 -> ResultInfo(
                title = "¡Vas por buen camino!",
                description = "¡Felicidades! Eres muy responsable en tu consumo. Para ti el medio ambiente está presente en cada decisión de compra...",
                treeMessage = "Produces ${score.toInt()}kg CO2e.\nTus hábitos de consumo nos hacen ganar 66 árboles al año.",
                color = Color(0xFF005100)
            )

            score <= 4000 -> ResultInfo(
                title = "¡Aún puedes mejorar!",
                description = "Sabes que deberías usar más bici, planificar mejor tus compras, evitar plásticos desechables...",
                treeMessage = "Produces ${score.toInt()}kg CO2e.\nTus emisiones equivalen a cortar 66 árboles al año.",
                color = Color(0xFFE5B602)
            )

            score <= 6000 -> ResultInfo(
                title = "¡Tus hábitos hacen peligrar el planeta!",
                description = "Cada compra que haces, cada decisión sobre tu alimentación o el transporte que usas impacta positiva o negativamente al mundo...",
                treeMessage = "Produces ${score.toInt()}kg CO2e.\nTus emisiones equivalen a cortar 99 árboles al año.",
                color = Color(0xFFD36709)
            )

            else -> ResultInfo(
                title = "¡Tus hábitos devastan el planeta!",
                description = "¿Has pensado que tus actividades diarias afectan al planeta y que está en tus manos evitarlo?...",
                treeMessage = "Produces ${score.toInt()}kg CO2e.\nTus emisiones equivalen a cortar más de 100 árboles al año.",
                color = Color(0xFFC4250F)
            )
        }
    }

    fun resetCalculator() {
        electronicScore = 0.0
        gasScore = 0.0
        bulbScore = 0.0
        timeTransport = 1.0
        flightScore = 0.0
        plasticScore = 0.0
        clothingScore = 0.0

        isFlightStepReady = false
        isPlasticStepReady = false
        isClothingStepReady = false

        selectedTransportEmissions.clear()

        meatSelections.clear()
        meatScores.clear()

        deviceSelections.clear()
        deviceScores.clear()
    }
}

data class ResultInfo(
    val title: String,
    val description: String,
    val treeMessage: String,
    val color: Color
)
