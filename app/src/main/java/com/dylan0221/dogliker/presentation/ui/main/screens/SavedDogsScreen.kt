package com.dylan0221.dogliker.presentation.ui.main.screens

import com.dylan0221.dogliker.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dylan0221.dogliker.presentation.navigation.Screen
import com.dylan0221.dogliker.presentation.states.DogItemState
import com.dylan0221.dogliker.presentation.ui.main.sharedcomposables.Background
import com.dylan0221.dogliker.presentation.ui.main.sharedcomposables.CardImage
import com.dylan0221.dogliker.presentation.ui.main.sharedcomposables.NavigationButton
import com.dylan0221.dogliker.presentation.viewmodels.screens.SavedDogsViewModel


@ExperimentalCoilApi
@Composable
fun SavedDogsScreen(navController: NavController, viewModel: SavedDogsViewModel = hiltViewModel()) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (homeButton) = createRefs()

        Background()

        FavoriteDogList(dogList = viewModel.dogs.value)


        NavigationButton(
            onClick = { navController.navigate(Screen.HomeScreen.route) },
            image = painterResource(id = R.drawable.ic_baseline_home_24),
            description = "Goes to HomeScreen",
            modifier = Modifier
                .offset(y = -125.dp)
                .constrainAs(homeButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            dp = 50.dp
        )
    }
}


@ExperimentalCoilApi
@Composable
fun FavoriteDogList(dogList: List<DogItemState>) {


    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(top = 150.dp)
    ) {
        itemsIndexed(dogList) { index, item ->
            CardImage(
                dogItem = item,
                contentDescription = "liked Dog #$index"
            )
            Spacer(modifier = Modifier.size(20.dp))
        }
    }


}
