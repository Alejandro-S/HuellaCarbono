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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.R
import com.asalazar.huellacarbono.ui.compose.util.CardTextTitle
import com.asalazar.huellacarbono.ui.compose.util.InfoCard
import com.asalazar.huellacarbono.ui.compose.util.MainTitleText
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun AboutScreen() {
    val greenpeaceUrl = "https://consumoresponsable.greenpeace.org.mx/calcula-tu-huella-de-carbono"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        MainTitleText(stringResource(R.string.title_about_screen))

        InfoCard(modifier = Modifier.fillMaxWidth()) {
            CardTextTitle(stringResource(R.string.title_about_info))

            Text(
                text = stringResource(R.string.label_about_info),
                style = MaterialTheme.typography.bodyMedium
            )

            ReferenceItem(
                text = "Ver calculadora original de Greenpeace",
                url = greenpeaceUrl
            )
        }

        Text(
            text = "Desarrollado para el Programa de Certificación \nAndroid Software Developer",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
        Text(
            "Por Alejandro Salazar Zavala \n salazaralejandro767@gmail.com",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Tecnológico de Monterrey",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Preview
@Composable
private fun AboutScreenPreview() {
    HuellaCarbonoTheme {
        AboutScreen()
    }
}
