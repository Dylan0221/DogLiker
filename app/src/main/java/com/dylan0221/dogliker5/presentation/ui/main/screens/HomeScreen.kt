package com.dylan0221.dogliker5.presentation.ui.main.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dylan0221.dogliker5.presentation.navigation.Screen
import com.dylan0221.dogliker5.presentation.ui.main.sharedcomposables.Background
import com.dylan0221.dogliker5.presentation.ui.main.sharedcomposables.CardImage
import com.dylan0221.dogliker5.presentation.ui.main.sharedcomposables.NavigationButton
import com.dylan0221.dogliker5.presentation.ui.theme.DogLiker5Theme
import com.dylan0221.dogliker5.presentation.viewmodels.screens.HomeScreenViewModel

@Preview
@Composable
fun HomeScreenPreview() {
    DogLiker5Theme {
    }
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = hiltViewModel()) {

    Background()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image, topButton, lowerButton, likeIcon) = createRefs()


        //navigates to Saved dogs screen
        NavigationButton(
            onClick = { navController.navigate(Screen.SavedDogsScreen.route) },
            image = painterResource(id = com.dylan0221.dogliker5.R.drawable.ic_baseline_favorite_24),
            description = "Goes to SavedDogsScreen",
            modifier = Modifier
                .offset(y = 125.dp)
                .constrainAs(lowerButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            dp = -50.dp
        )

        AnimatedVisibility(
            visible = viewModel.cardImageAnimationState.value,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut(),
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {

            CardImage(
                dogItem = viewModel.dogItem.value,
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = { viewModel.likeDog() },
                            onLongPress = { viewModel.changeDog() }
                        )
                    },
                contentDescription = "Dog Item"

            )
        }

        //navigates to login registration screen
        NavigationButton(
            onClick = { navController.navigate(Screen.LoginRegistrationScreen.route) },
            image = painterResource(id = com.dylan0221.dogliker5.R.drawable.ic_baseline_login_24),
            description = "Goes to LoginRegistrationScreen",
            modifier = Modifier
                .offset(y = -125.dp)
                .constrainAs(topButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            dp = 50.dp
        )

        //invisible like icon
        //only visible when the image is liked
        LikeIcon(
            modifier = Modifier
                .constrainAs(likeIcon) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .offset(y = 20.dp),
            viewModel = viewModel
        )

    }
}

@Composable
fun LikeIcon(modifier: Modifier = Modifier, viewModel: HomeScreenViewModel) {

    val isLiked = viewModel.dogItem
    ConstraintLayout(modifier = modifier) {

        AnimatedVisibility(visible = isLiked.value.success?.isLiked ?: false) {
            Box() {
                Image(painter = painterResource(id = com.dylan0221.dogliker5.R.drawable.ic_baseline_favorite_24_red),
                    contentDescription = "like button",
                    modifier = Modifier.size(75.dp))
            }


        }

    }

}

