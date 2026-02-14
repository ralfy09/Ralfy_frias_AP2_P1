package com.example.ralfy_frias_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import com.example.ralfy_frias_ap2_p1.presentacion.navigation.CervezaNavHost
import com.example.ralfy_frias_ap2_p1.ui.theme.Ralfy_frias_AP2_P1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Ralfy_frias_AP2_P1Theme {
                CervezaNavHost()
            }
        }
    }
}