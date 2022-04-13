package com.dylan0221.dogliker5.presentation.ui.main.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.dylan0221.dogliker5.presentation.navigation.Screen
import com.dylan0221.dogliker5.presentation.states.LoginRegistrationScreenState
import com.dylan0221.dogliker5.presentation.ui.main.sharedcomposables.Background
import com.dylan0221.dogliker5.presentation.ui.main.sharedcomposables.NavigationButton
import com.dylan0221.dogliker5.presentation.ui.theme.Black_Coffee
import com.dylan0221.dogliker5.presentation.ui.theme.Snow
import com.dylan0221.dogliker5.presentation.ui.theme.Steel_Blue
import com.dylan0221.dogliker5.presentation.viewmodels.screens.LoginRegistrationScreenViewModel
import com.dylan0221.dogliker5.utils.constants.TextFieldLayoutTypes
import com.dylan0221.dogliker5.R
import com.dylan0221.dogliker5.presentation.ui.theme.Imperial_Red

@Composable
fun LoginRegistrationScreen(
    viewModel: LoginRegistrationScreenViewModel = hiltViewModel(),
    navController: NavController,
) {

    Background()

    //changes depending on the screen state
    when (viewModel.screenState.value) {
        LoginRegistrationScreenState.LogIn -> {
            LogInState(viewModel, navController = navController)
        }

        LoginRegistrationScreenState.LoggedIn -> {
            LoggedInState(viewModel, navController = navController)
        }

        LoginRegistrationScreenState.Registration -> {
            RegistrationState(viewModel, navController = navController)
        }

    }


}

@Composable
fun RegistrationState(
    viewModel: LoginRegistrationScreenViewModel,
    navController: NavController,
) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (homeButton, textFields) = createRefs()


        Column(modifier =
        Modifier
            .fillMaxSize()
            .offset(y = -325.dp)
            .constrainAs(textFields) {},
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            AnimatedVisibility(visible = viewModel.invalidInputsState.value.visibility) {
                ErrorText(
                    text = viewModel.invalidInputsState.value.message
                )

            }

            TextFieldLayout(
                type = TextFieldLayoutTypes.EMAIL,
                onValueChange = { viewModel.changeEmail(it) },
                value = viewModel.email.value
            )

            TextFieldLayout(type = TextFieldLayoutTypes.PASSWORD,
                onValueChange = { viewModel.changePassword(it) },
                value = viewModel.password.value
            )

            TextFieldLayout(
                type = TextFieldLayoutTypes.CONFIRM_PASSWORD,
                onValueChange = { viewModel.changeConfirmPassword(it) },
                value = viewModel.confirmPassword.value
            )

            Spacer(modifier = Modifier.height(12.dp))

            ButtonLayout(
                text = "Sign Up",
                onClick = { viewModel.signUpEmail() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Steel_Blue,
                    contentColor = Snow)
            )

            ButtonLayout(
                text = "Already have an account?",
                onClick = { viewModel.changeScreenState(LoginRegistrationScreenState.LogIn) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Snow,
                    contentColor = Black_Coffee)
            )
        }

        NavigationButton(
            onClick = { navController.navigate(Screen.HomeScreen.route) },
            image = painterResource(id = R.drawable.ic_baseline_home_24),
            description = "Goes to HomeScreen",
            modifier = Modifier
                .offset(y = 125.dp)
                .constrainAs(homeButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            dp = -50.dp

        )
    }

}

@Composable
fun LoggedInState(
    viewModel: LoginRegistrationScreenViewModel,
    navController: NavController,
) {


    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (homeButton, text, logOutButton) = createRefs()



        Text(
            text = "Welcome!",
            fontSize = 50.sp,
            color = Black_Coffee,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .offset(y = -25.dp)
                .constrainAs(text) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .background(color = Color.White)
        )

        ButtonLayout(
            text = "Log Out",
            onClick = { viewModel.signOut() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Steel_Blue, contentColor = Snow),
            modifier = Modifier.constrainAs(logOutButton) {
                bottom.linkTo(homeButton.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(text.bottom)
            })



        NavigationButton(
            onClick = { navController.navigate(Screen.HomeScreen.route) },
            image = painterResource(id = R.drawable.ic_baseline_home_24),
            description = "Goes to HomeScreen",
            modifier = Modifier
                .offset(y = 125.dp)
                .constrainAs(homeButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            dp = -50.dp

        )

    }
}

@Composable
fun LogInState(
    viewModel: LoginRegistrationScreenViewModel,
    navController: NavController,

    ) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (homeButton, textFields) = createRefs()




        Column(modifier =
        Modifier
            .fillMaxSize()
            .offset(y = -375.dp)
            .constrainAs(textFields) {},
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            AnimatedVisibility(visible = viewModel.invalidInputsState.value.visibility) {
                ErrorText(
                    text = viewModel.invalidInputsState.value.message
                )
            }
            TextFieldLayout(
                type = TextFieldLayoutTypes.EMAIL,
                onValueChange = { viewModel.changeEmail(it) },
                value = viewModel.email.value
            )

            TextFieldLayout(
                type = TextFieldLayoutTypes.PASSWORD,
                value = viewModel.password.value,
                onValueChange = { viewModel.changePassword(it) }
            )

            Spacer(modifier = Modifier.height(12.dp))
            ButtonLayout(
                text = "Log In",
                onClick = { viewModel.logInEmail() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Steel_Blue,
                    contentColor = Snow)

            )
            ButtonLayout(
                text = "Don't have an account?",
                onClick = { viewModel.changeScreenState(LoginRegistrationScreenState.Registration) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Snow,
                    contentColor = Black_Coffee)

            )

        }

        NavigationButton(
            onClick = { navController.navigate(Screen.HomeScreen.route) },
            image = painterResource(id = R.drawable.ic_baseline_home_24),
            description = "Goes to HomeScreen",
            modifier = Modifier
                .offset(y = 125.dp)
                .constrainAs(homeButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            dp = -50.dp

        )
    }


}

@Composable
fun TextFieldLayout(
    type: TextFieldLayoutTypes,
    value: String,
    onValueChange: (String) -> Unit,
) {


    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = when (type) {
                TextFieldLayoutTypes.EMAIL -> "Email"
                TextFieldLayoutTypes.PASSWORD -> "Password"
                TextFieldLayoutTypes.CONFIRM_PASSWORD -> "Confirm Password"
            })
        },
        placeholder = {
            Text(text = when (type) {
                TextFieldLayoutTypes.EMAIL -> "Example@email.com"
                TextFieldLayoutTypes.PASSWORD -> "Password"
                TextFieldLayoutTypes.CONFIRM_PASSWORD -> "Confirm Password"


            })
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType =
            when (type) {
                TextFieldLayoutTypes.EMAIL -> KeyboardType.Email
                TextFieldLayoutTypes.CONFIRM_PASSWORD -> KeyboardType.Password
                TextFieldLayoutTypes.PASSWORD -> KeyboardType.Password
            }
        ),
        modifier = Modifier.background(color = Color.White),
        visualTransformation = when (type) {
            TextFieldLayoutTypes.PASSWORD -> PasswordVisualTransformation()
            TextFieldLayoutTypes.CONFIRM_PASSWORD -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Steel_Blue,
            unfocusedBorderColor = Black_Coffee,
            focusedLabelColor = Steel_Blue,
            unfocusedLabelColor = Black_Coffee,
            placeholderColor = Black_Coffee,
            cursorColor = Steel_Blue
        )
    )
}


@Composable
fun ButtonLayout(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
) {

    Button(
        onClick = onClick,
        modifier = modifier.width(250.dp),
        colors = colors

    ) {
        Text(text = text)
    }
}

@Composable
fun ErrorText(modifier: Modifier = Modifier, text: String) {

    Text(
        text = text,
        color = Imperial_Red,
        textAlign = TextAlign.Center,
        modifier = modifier.background(color = Color.White),
        fontSize = 16.sp

    )
}