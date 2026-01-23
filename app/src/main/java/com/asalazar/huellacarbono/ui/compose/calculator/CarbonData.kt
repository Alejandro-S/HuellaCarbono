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

data class Appliance(
    val id: Int,
    val name: String,
    val emissions: Double
)

object CarbonData {
    // 1. APARATOS ELECTRÓNICOS (IDs 101-113)
    val electronicAppliances = listOf(
        Appliance(101, "Refrigerador", 237.0),
        Appliance(102, "Horno de microondas", 19.6),
        Appliance(103, "Computadora", 128.0),
        Appliance(104, "Laptop", 64.0),
        Appliance(105, "Consola de videojuegos", 20.0),
        Appliance(106, "Estéreo", 3.0),
        Appliance(107, "Televisión", 82.0),
        Appliance(108, "Lavadora", 21.0),
        Appliance(109, "Secadora de ropa", 57.0),
        Appliance(110, "Lavatrastes", 68.0),
        Appliance(111, "Aspiradora", 35.0),
        Appliance(112, "Aire acondicionado", 227.0),
        Appliance(113, "Ventiladores", 96.0)
    )

    // 2. GAS (IDs 201-202) Y 3. FOCOS (IDs 211-214)
    val gasOptions = listOf(
        Appliance(201, "Gas Natural", 189.0),
        Appliance(202, "Gas LP", 153.0)
    )

    val bulbOptions = listOf(
        Appliance(211, "LED", 12.0),
        Appliance(212, "Ahorradores", 31.0),
        Appliance(213, "Incandescentes", 61.0),
        Appliance(214, "No sé", 35.0)
    )

    // 4. TRANSPORTE (IDs 301-307)
    val transportOptions = listOf(
        Appliance(301, "Auto", 4160.0),
        Appliance(302, "Autobús", 310.0),
        Appliance(303, "Bici / Caminando", 0.0),
        Appliance(304, "Combi", 1090.0),
        Appliance(305, "Metro", 0.0),
        Appliance(306, "Metrobús", 90.0),
        Appliance(307, "Moto", 2080.0)
    )

    // 5. TIEMPO DE TRASLADO (IDs 351-357) - Multiplicadores
    val timeMultipliers = listOf(
        Appliance(351, "Menos de 10 min", 0.08),
        Appliance(352, "10 a 20 min", 0.25),
        Appliance(353, "20 a 40 min", 0.5),
        Appliance(354, "40 min a 1 hr", 0.83),
        Appliance(355, "1.5 hrs", 1.5),
        Appliance(356, "2 hrs", 2.5),
        Appliance(357, "Más de 3 hrs", 3.5)
    )

    // 6. VUELOS (IDs 401-405)
    val flightOptions = listOf(
        Appliance(401, "Viajero frecuente, más de dos vuelos internacionales y dos nacionales al año", 4600.0),
        Appliance(402, "Aproximadamente un vuelo (ida y vuelta) internacional al año", 2000.0),
        Appliance(403, " Más de un vuelo (ida y vuelta) nacional al año", 500.0),
        Appliance(404, "Aproximadamente un vuelo (ida y vuelta) nacional al año", 200.0),
        Appliance(405, "No suelo volar", 0.0)
    )

    val plasticOptions = listOf(
        Appliance(501, "Diario.", 8.0),
        Appliance(502, "De vez en cuando - 1 vez por semana.", 3.0),
        Appliance(503, "Rara vez.", 1.0),
        Appliance(504, "No uso plástico.", 0.0)
    )

    val clothingOptions = listOf(
        Appliance(601, "Siempre es nueva y de importación.", 403.0),
        Appliance(602, "Siempre es nueva y nacional.", 254.0),
        Appliance(603, "De segunda mano.", 127.0),
        Appliance(604, "La reparo y reutilizo.", 85.0)
    )
}

object CarbonMatrixData {
    // 7. CONSUMO DE CARNE (Prefijo 7000)
    fun getMeatOptions(type: String): List<Appliance> {
        val (baseId, emissions) = when(type) {
            "Res" -> 7100 to listOf(2360.0, 1865.0, 675.0, 0.0)
            "Cerdo" -> 7200 to listOf(540.0, 386.0, 155.0, 0.0)
            "Pollo" -> 7300 to listOf(344.0, 245.0, 98.0, 0.0)
            else -> 7400 to listOf(128.0, 91.0, 37.0, 0.0) // Pescado
        }
        val labels = listOf("Diario", "4-6 días", "1-3 días", "No consumo")

        return labels.mapIndexed { index, label ->
            Appliance(id = baseId + index, name = label, emissions = emissions[index])
        }
    }

    // 10. RENOVACIÓN DE DISPOSITIVOS (Prefijo 8000)
    fun getDeviceOptions(device: String): List<Appliance> = when (device) {
        "Celular" -> listOf(
            Appliance(701, "Cada 6 meses", 120.0),
            Appliance(702, "Cada año", 60.0),
            Appliance(703, "De 2 a 3 años", 25.0),
            Appliance(704, "Más de 3 años", 15.0),
            Appliance(705, "No tengo este dispositivo", 0.0)
        )
        "Tableta" -> listOf(
            Appliance(711, "Cada 6 meses", 340.0),
            Appliance(712, "Cada año", 170.0),
            Appliance(713, "De 2 a 3 años", 68.0),
            Appliance(714, "Más de 3 años", 43.0),
            Appliance(715, "No tengo este dispositivo", 0.0)
        )
        "Computadora" -> listOf(
            Appliance(721, "Cada 6 meses", 700.0),
            Appliance(722, "Cada año", 350.0),
            Appliance(723, "De 2 a 3 años", 140.0),
            Appliance(724, "Más de 3 años", 88.0),
            Appliance(725, "No tengo este dispositivo", 0.0)
        )
        "Consola de videojuegos" -> listOf(
            Appliance(731, "Cada 6 meses", 180.0),
            Appliance(732, "Cada año", 90.0),
            Appliance(733, "De 2 a 3 años", 36.0),
            Appliance(734, "Más de 3 años", 22.0),
            Appliance(735, "No tengo este dispositivo", 0.0)
        )
        else -> emptyList()
    }
}
