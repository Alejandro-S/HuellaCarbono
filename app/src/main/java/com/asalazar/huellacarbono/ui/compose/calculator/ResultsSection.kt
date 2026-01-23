package com.asalazar.huellacarbono.ui.compose.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asalazar.huellacarbono.ui.compose.util.MainTitleText

@Composable
fun ResultsSection(
    result: ResultInfo,
    sumHome: Double,
    colorHome: Color,
    sumTransport: Double,
    colorTransport: Color,
    sumConsumption: Double,
    colorConsumption: Color,
    onRestart: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainTitleText("RESULTADO")

        Text(
            text = result.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight =  FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = result.color
        )

        Spacer(modifier = Modifier.height(24.dp))

        CategoryBar("Hogar", sumHome, 854.0, colorHome)
        CategoryBar("Transporte", sumTransport, 4080.0, colorTransport)
        CategoryBar("Consumo", sumConsumption, 2249.0, colorConsumption)

        Spacer(modifier = Modifier.height(32.dp))

        Surface(
            color = result.color.copy(alpha = 0.1f),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = result.treeMessage,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = result.color
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = result.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onRestart,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3AAE2A))
        ) {
            Text("VOLVER A EMPEZAR")
        }
    }
}

@Composable
fun CategoryBar(label: String, score: Double, max: Double, color: Color) {
    val progress = (score / max).coerceIn(0.0, 1.0).toFloat()

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(label, style = MaterialTheme.typography.bodySmall)
            Text("${(progress * 100).toInt()}%", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
        }
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
            color = color,
            trackColor = Color.LightGray.copy(alpha = 0.3f)
        )
    }
}
