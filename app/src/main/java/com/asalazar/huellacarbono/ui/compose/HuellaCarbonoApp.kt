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

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.asalazar.huellacarbono.R
import com.asalazar.huellacarbono.ui.compose.calculator.CalculatorScreen
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme


object Routers {

    val bottomOptions = listOf(Routers.Home, Calculator, About)

    interface IBottomOption {
        val value: String
        val label: Int
        val icon: ImageVector
    }

    object Home : IBottomOption {
        override val value = "home"
        override val label: Int = R.string.menu_home_item
        override val icon: ImageVector = Icons.Default.Home
    }

    object Calculator : IBottomOption {
        override val value = "calculator"
        override val label: Int = R.string.menu_calculator_item
        override val icon: ImageVector = Icons.Outlined.Calculate
    }

    object PillarDetail {
        const val Value = "pillar_detail"
        const val Param = "pillar"
    }

    object About : IBottomOption {
        override val value = "about"
        override val label: Int = R.string.menu_about_it
        override val icon: ImageVector = Icons.Default.Info
    }
}

@Composable
fun HuellaCarbonoApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val currentRoute by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = { TopBar(currentRoute?.destination?.route) { navController.navigateUp() } },
        bottomBar = {
            BottomNavigationBar(currentRoute?.destination?.route) { route: String ->
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Routers.Home.value,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            composable(route = Routers.Home.value) {
                HomeScreen { pillar -> navController.navigate("${Routers.PillarDetail.Value}/$pillar") }
            }
            composable(route = Routers.Calculator.value) { CalculatorScreen() }
            composable(route = "${Routers.PillarDetail.Value}/{${Routers.PillarDetail.Param}}") { entry ->
                val pillar = entry.arguments?.getString(Routers.PillarDetail.Param) ?: ""
                PillarDetail(pillar)
            }
            composable(route = Routers.About.value) { AboutScreen() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    currentRoute: String?,
    modifier: Modifier = Modifier,
    onBackStack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.app_name), fontWeight = FontWeight.Bold) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        navigationIcon = {
            if (!Routers.bottomOptions.any { it.value == currentRoute }) {
                IconButton(onClick = onBackStack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    navTo: (String) -> Unit
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceVariant) {
        Routers.bottomOptions.forEach { option ->
            NavigationBarItem(
                icon = { Icon(option.icon, contentDescription = null) },
                label = { Text(stringResource(option.label)) },
                selected = currentRoute == option.value,
                onClick = { navTo(option.value) }
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    HuellaCarbonoTheme {
        BottomNavigationBar(Routers.Calculator.value) { }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    HuellaCarbonoTheme {
        TopBar(Routers.Calculator.value) {}
    }
}
