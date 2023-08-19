package com.arcane.anboxsync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arcane.anboxsync.ui.theme.AnBoxSyncTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value
            }
        }

        setContent {
            AnBoxSyncTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    FirebaseApp.initializeApp(this)
                    AnboxsyncApp()
                }
            }
        }
    }
}
