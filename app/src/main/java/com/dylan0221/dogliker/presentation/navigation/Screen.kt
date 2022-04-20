package com.dylan0221.dogliker.presentation.navigation

sealed class Screen(val route: String) {

    object HomeScreen: Screen("Home_Screen")
    object LoginRegistrationScreen: Screen("Login_Registration_Screen")
    object SavedDogsScreen: Screen("Saved_Dogs_Screen")
}