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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.asalazar.huellacarbono.R
import com.asalazar.huellacarbono.ui.theme.HuellaCarbonoTheme

val home = "home"
val calculator = "calculator"

@Composable
fun HuellaCarbonoApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val currentRoute by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = { TopBar() },
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
            startDestination = home,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            composable(route = home) { HomeScreen() }
            composable(route = calculator) { CalculatorScreen() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar({ Text(stringResource(R.string.app_name), modifier = modifier) })
}

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    navTo: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(R.string.menu_home_item)) },

            selected = currentRoute == home,
            onClick = { navTo(home) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Build, contentDescription = null) },
            label = { Text(stringResource(R.string.menu_calculator_item)) },
            selected = currentRoute == calculator,
            onClick = { navTo(calculator) }
        )
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    HuellaCarbonoTheme {
        BottomNavigationBar(calculator) { }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    HuellaCarbonoTheme {
        TopBar()
    }
}
