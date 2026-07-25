package com.example.curriculum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.curriculum.ui.navigation.AppNavigation
import com.example.curriculum.ui.theme.CurriculumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurriculumTheme {
                AppNavigation()
            }
        }
    }
}
