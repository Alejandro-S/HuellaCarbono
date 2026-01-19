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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.R
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun WhyReduceIt(modifier: Modifier = Modifier) {
    Card(modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            WhyReduceItTitle()
            WhyReduceItBody()
        }
    }
}

@Composable
fun WhyReduceItTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.title_why_reduce),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@Composable
fun WhyReduceItBody(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.label_why_reduce),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Preview
@Composable
private fun WhyReduceItPreview() {
    HuellaCarbonoTheme {
        WhyReduceIt()
    }
}
