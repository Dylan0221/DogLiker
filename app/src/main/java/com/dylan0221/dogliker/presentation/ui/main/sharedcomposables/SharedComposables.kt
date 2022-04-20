package com.dylan0221.dogliker.presentation.ui.main.sharedcomposables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dylan0221.dogliker.R
import com.dylan0221.dogliker.presentation.states.DogItemState
import com.dylan0221.dogliker.presentation.ui.theme.Black_Coffee
import com.dylan0221.dogliker.presentation.ui.theme.DogLiker5Theme

@Preview
@Composable
fun SharedComposablePreview() {

    DogLiker5Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {
            Background()


            Column {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,

                    ) {
                    CircularProgressIndicator()
                }
            }

        }
    }
}

@ExperimentalCoilApi
@Composable
fun CardImage(
    dogItem: DogItemState,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {

    ConstraintLayout(modifier = modifier) {
        Card(
            shape = RoundedCornerShape(18),
            modifier = modifier
                .height(300.dp)
                .width(300.dp)
                .border(5.dp, Color.Black, RoundedCornerShape(18)),
            backgroundColor = Color.White,


            ) {
            when {
                dogItem.loading -> {
                    CircularProgressIndicator(modifier = Modifier.fillMaxSize(0.5f))
                }
                dogItem.success != null -> {
                    Image(painter = rememberImagePainter(dogItem.success.image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                else -> {
                    Image(painter = rememberImagePainter(R.drawable.ic_baseline_broken_image_24),
                        contentDescription = null)
                }
            }

        }
    }


}


@Composable
fun Background(modifier: Modifier = Modifier.fillMaxSize()) {


    val painter: Painter = painterResource(id = R.drawable.app_background)

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.FillBounds
    )


}

// Button used to navigate between screens
//
@Composable
fun NavigationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    image: Painter,
    description: String,
    dp: Dp,
) {


    ConstraintLayout(modifier = modifier) {


        Surface(
            color = Black_Coffee,
            shape = CircleShape,
            modifier = Modifier
                .size(250.dp)
                .clickable(onClick = onClick)


        ) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()) {
                Image(painter = image,
                    contentDescription = description,
                    modifier = Modifier
                        .size(75.dp)
                        .offset(y = dp))

            }
        }
    }

}
