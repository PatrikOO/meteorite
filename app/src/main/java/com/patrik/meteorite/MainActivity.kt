package com.patrik.meteorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.patrik.meteorite.ui.LoadingSpinner
import com.patrik.meteorite.ui.NavigationItem
import com.patrik.meteorite.ui.list.MeteoriteListScreen
import com.patrik.meteorite.ui.map.MeteoriteMapScreen
import com.patrik.meteorite.ui.theme.MeteoriteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel by viewModels()
            val screenViewState = viewModel.meteoritesScreenViewState.collectAsState()
            val viewState = screenViewState.value

            MeteoriteTheme {
                val navController = rememberNavController()

                val locationPermissions = rememberMultiplePermissionsState(
                    permissions = listOf(
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )

                LaunchedEffect(key1 = locationPermissions.allPermissionsGranted) {
                    if (locationPermissions.allPermissionsGranted) {
                        viewModel.getCurrentLocation()
                    } else {
                        locationPermissions.launchMultiplePermissionRequest()
                    }
                }

                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) },
                    content = { padding ->
                        Box(modifier = Modifier.padding(padding)) {
                            Navigation(
                                navController = navController,
                                viewState = viewState
                            )
                        }
                    },
                )
            }
        }
    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    viewState: MeteoritesScreenViewState
) {
    NavHost(navController, startDestination = NavigationItem.MapScreen.route) {
        composable(NavigationItem.MapScreen.route) {

            when (viewState) {
                MeteoritesScreenViewState.Loading -> LoadingSpinner(PaddingValues(10.dp))

                is MeteoritesScreenViewState.MeteoritesList -> {
                    MeteoriteMapScreen(
                        meteoritesScreenViewState = viewState
                    )
                }
            }
        }
        composable(NavigationItem.ListScreen.route) {
            when (viewState) {
                MeteoritesScreenViewState.Loading -> LoadingSpinner(PaddingValues(10.dp))

                is MeteoritesScreenViewState.MeteoritesList -> {
                    MeteoriteListScreen(
                        meteoritesScreenViewState = viewState
                    )
                }
            }
        }
    }
}
