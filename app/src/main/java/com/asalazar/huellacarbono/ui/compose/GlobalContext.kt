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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.compose.util.CardTextTitle
import com.asalazar.huellacarbono.ui.compose.util.InfoCard
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme


private val bullets = arrayOf(
    "Promedio mundial: 4.8 toneladas/año por persona.",
    "Meta sostenible (2050): 2.0 toneladas/año.",
    "Impacto: El transporte representa casi el 27% de las emisiones globales."
)

@Composable
fun GlobalContext(modifier: Modifier = Modifier) {
    InfoCard(modifier) {
        GlobalContextTitle()
        GlobalContextBody()
    }
}

@Composable
fun GlobalContextBody(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        bullets.forEachIndexed { index, info ->
            key(index) {
                BulletPoint(info)
            }
        }
    }
}

@Composable
fun GlobalContextTitle(modifier: Modifier = Modifier) {
    CardTextTitle("Contexto Global (2024)", modifier = modifier)
}

@Composable
fun BulletPoint(text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(text = "•", modifier = Modifier.padding(end = 8.dp), fontWeight = FontWeight.Bold)
        Text(text = text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
private fun GlobalContextPreview() {
    HuellaCarbonoTheme {
        GlobalContext()
    }
}
