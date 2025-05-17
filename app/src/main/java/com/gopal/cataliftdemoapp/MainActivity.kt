package com.gopal.cataliftdemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gopal.cataliftdemoapp.ui.theme.CataLiftDemoAppTheme
import com.gopal.cataliftdemoapp.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CataLiftDemoAppTheme {
                NavGraph()
            }
        }
    }
}