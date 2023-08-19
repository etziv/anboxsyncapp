package com.arcane.anboxsync.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main")
    object ManualScreen : Screen("wifi")
}