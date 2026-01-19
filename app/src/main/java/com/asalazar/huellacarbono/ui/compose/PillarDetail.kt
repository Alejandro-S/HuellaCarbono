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

package com.asalazar.huellacarbono.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.R
import com.asalazar.huellacarbono.ui.compose.util.CardTextTitle
import com.asalazar.huellacarbono.ui.compose.util.InfoCard
import com.asalazar.huellacarbono.ui.compose.util.MainTitleText
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun PillarDetail(pillar: String) {
    val pillarInfo = remember(pillar) { getPillarInfo(pillar) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column {
            Icon(pillarInfo.icon, modifier = Modifier.size(60.dp), contentDescription = null)
            MainTitleText(pillarInfo.title)
        }
        Text(
            text = pillarInfo.description,
            style = MaterialTheme.typography.bodyLarge
        )
        InfoCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            CardTextTitle(stringResource(R.string.title_tips))
            pillarInfo.tips.forEachIndexed { index, tip ->
                BulletPoint(tip)
            }
        }
    }
}

//TODO: Create enum Pillar
private fun getPillarInfo(pillar: String): PillarInfo {
    return when (pillar) {
        pillarLabels[0] -> PillarInfo(
            "Transporte y Movilidad",
            "El transporte representa cerca de una cuarta parte de las emisiones mundiales de CO2 relacionadas con la energía. Los desplazamientos en coche privado y los vuelos son los mayores contribuyentes.",
            Icons.Default.DirectionsCar,
            listOf(
                "Usa bicicleta o camina para trayectos cortos.",
                "Prefiere el transporte público.",
                "Comparte coche (carpooling).",
                "Reduce los vuelos de larga distancia."
            )
        )

        pillarLabels[1] -> PillarInfo(
            "Energía en el Hogar",
            "La electricidad y la calefacción generan grandes cantidades de emisiones debido a la quema de carbón, petróleo y gas. Hacer tu hogar más eficiente es clave.",
            Icons.Default.Lightbulb,
            listOf(
                "Cambia a bombillas LED.",
                "Apaga luces que no uses.",
                "Revisa el aislamiento térmico.",
                "Desconecta aparatos en 'stand-by'."
            )
        )

        pillarLabels[2] -> PillarInfo(
            "Consumo y Estilo de Vida",
            "Lo que compramos, comemos y vestimos tiene un costo oculto. La industria de la moda y la producción de carne son responsables de un alto porcentaje de emisiones y uso de agua.",
            Icons.Default.ShoppingCart,
            listOf(
                "Reduce el consumo de carne roja.",
                "Compra productos locales (Km 0).",
                "Evita el 'Fast Fashion'.",
                "Aplica las 3R: Reducir, Reutilizar, Reciclar."
            )
        )

        else -> throw IllegalArgumentException("Invalid $pillar")
    }
}

data class PillarInfo(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val tips: List<String>
)

@Preview
@Composable
private fun PillarDetailPreview() {
    HuellaCarbonoTheme {
        PillarDetail(pillarLabels.first())
    }
}
