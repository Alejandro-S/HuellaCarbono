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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private var electronicScore by mutableDoubleStateOf(0.0)
    private var gasScore by mutableDoubleStateOf(0.0)
    private var bulbScore by mutableDoubleStateOf(0.0)
    private var selectedTransportEmissions = mutableStateListOf<Double>()
    private var timeTransport by mutableDoubleStateOf(0.0)
    private var flightScore by mutableDoubleStateOf(0.0)

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

    fun calculateResult(
        homeSum: Double,
        transportSum: Double,
        consumptionSum: Double
    ): Triple<String, String, String> {
        val total = homeSum + transportSum + consumptionSum

        return when {
            total <= 2000 -> Triple(
                "¡Vas por buen camino!",
                "Felicidades, eres muy responsable. Si todos consumieran como tú, combatiríamos el cambio climático.",
                "Produces ${total.toInt()}kg CO2e. ¡Ganas 66 árboles al año!"
            )

            total in 2001.0..4000.0 -> Triple(
                "¡Aún puedes mejorar!",
                "Empiezas a modificar tus hábitos, pero aún no llegas a la meta. ¡Es tu momento de ser un actor de cambio!",
                "Produces ${total.toInt()}kg CO2e. Equivale a cortar 66 árboles al año."
            )

            total in 4001.0..6000.0 -> Triple(
                "¡Tus hábitos hacen peligrar el planeta!",
                "Piensas poco en el costo ambiental de tus decisiones. Si todos consumieran como tú, perderíamos biodiversidad rápidamente.",
                "Produces ${total.toInt()}kg CO2e. Equivale a cortar 99 árboles al año."
            )

            else -> Triple(
                "¡Tus hábitos devastan el planeta!",
                "Tus hábitos están muy por arriba del promedio. Priorizas la comodidad sobre el ambiente. ¡Aún puedes cambiar!",
                "Produces ${total.toInt()}kg CO2e. Equivale a cortar más de 100 árboles al año."
            )
        }
    }
}
