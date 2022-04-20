package com.dylan0221.dogliker.presentation.ui.main.mainactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import coil.annotation.ExperimentalCoilApi
import com.dylan0221.dogliker.presentation.navigation.SetUpNavGraph
import com.dylan0221.dogliker.presentation.ui.theme.DogLiker5Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalCoilApi::class)
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogLiker5Theme {
                SetUpNavGraph()
            }
        }
    }
}


