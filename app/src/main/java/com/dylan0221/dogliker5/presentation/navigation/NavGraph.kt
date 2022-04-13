package com.dylan0221.dogliker5.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.dylan0221.dogliker5.presentation.ui.main.screens.HomeScreen
import com.dylan0221.dogliker5.presentation.ui.main.screens.LoginRegistrationScreen
import com.dylan0221.dogliker5.presentation.ui.main.screens.SavedDogsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun SetUpNavGraph() {
    val navController = rememberAnimatedNavController()

    //NavHost for the transition animations from the accompanist navigation api
    //works Similar to the normal Nav Host
    AnimatedNavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(
            route = Screen.HomeScreen.route,
            enterTransition = {
                when (initialState.destination.route) {
                    //enters from Login Screen
                    Screen.LoginRegistrationScreen.route -> slideIntoContainer(AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(500))

                    //enters from the Saved Screen
                    Screen.SavedDogsScreen.route -> slideIntoContainer(AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(500))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    //Exits to Login Screen
                    Screen.LoginRegistrationScreen.route -> slideOutOfContainer(AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(500))

                    //Exits to Saved Screen
                    Screen.SavedDogsScreen.route -> slideOutOfContainer(AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(500))
                    else -> null
                }
            }
        ) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.LoginRegistrationScreen.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.HomeScreen.route -> slideIntoContainer(AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(500))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screen.HomeScreen.route -> slideOutOfContainer(AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(500))
                    else -> null
                }
            }
        ) {
            LoginRegistrationScreen(navController = navController)
        }
        composable(
            route = Screen.SavedDogsScreen.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Screen.HomeScreen.route -> slideIntoContainer(AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(500))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Screen.HomeScreen.route -> slideOutOfContainer(AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(500))
                    else -> null
                }
            }
        ) {
            SavedDogsScreen(navController = navController)
        }
    }

}