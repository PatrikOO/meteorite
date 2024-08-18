package com.patrik.meteorite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.patrik.meteorite.ui.MeteoriteListScreen
import com.patrik.meteorite.ui.MeteoriteMapScreen
import com.patrik.meteorite.ui.theme.MeteoriteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeteoriteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MeteoriteMapScreen(
//                        modifier = Modifier.padding(innerPadding)
//                    )

                    MeteoriteListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
