package com.patrik.meteoritelandings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.patrik.meteoritelandings.ui.MeteoriteScreen
import com.patrik.meteoritelandings.ui.theme.MeteoriteLandingsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeteoriteLandingsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MeteoriteScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
