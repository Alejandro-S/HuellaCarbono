package com.asalazar.huellacarbono.ui.compose

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.R
import com.asalazar.huellacarbono.ui.compose.util.MainTitleText
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

@Composable
fun Sources(modifier: Modifier = Modifier) {
    HorizontalDivider()
    Column(modifier = modifier) {
        MainTitleText(stringResource(R.string.title_sources))
        sources.forEachIndexed { index, source ->
            key(index) {
                val (label, url) = source
                ReferenceItem(label, url)
            }

        }
    }
}

@Composable
fun ReferenceItem(text: String, url: String) {
    val context = LocalContext.current
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                try {
                    val chooser = Intent.createChooser(intent, "Abrir con...")
                    context.startActivity(chooser)
                } catch (e: Exception) {
                    android.widget.Toast.makeText(
                        context,
                        context.getString(R.string.label_intent_error),
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }

            }
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Link,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textDecoration = TextDecoration.Underline
        )
    }
}

val sources = arrayOf(
    Pair(
        "Naciones Unidas (ONU) - Acción por el Clima",
        "https://www.un.org/es/actnow/facts-and-figures"
    ),
    Pair(
        "Agencia de Protección Ambiental (EPA)",
        "https://espanol.epa.gov/la-energia-y-el-medioambiente/emisiones-de-dioxido-de-carbono"
    ),
    Pair("Global Footprint Network", "https://www.footprintnetwork.org/")
)

@Preview
@Composable
private fun SourcesPreview() {
    HuellaCarbonoTheme {
        Sources()
    }
}
