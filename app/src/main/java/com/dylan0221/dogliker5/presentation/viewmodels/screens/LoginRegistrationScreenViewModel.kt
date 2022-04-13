package com.dylan0221.dogliker5.presentation.viewmodels.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylan0221.dogliker5.domain.use_cases.use_cases_pack.LoginRegistrationScreenUCPack
import com.dylan0221.dogliker5.presentation.states.InvalidInputState
import com.dylan0221.dogliker5.presentation.states.LoginRegistrationScreenState
import com.google.firebase.auth.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginRegistrationScreenViewModel @Inject constructor(
    private val uc: LoginRegistrationScreenUCPack,
) : ViewModel() {


    private val _invalidInputsState: MutableState<InvalidInputState> = mutableStateOf(
        InvalidInputState())
    val invalidInputsState: State<InvalidInputState> = _invalidInputsState


    private val _user: MutableState<FirebaseUser?> =
        mutableStateOf(FirebaseAuth.getInstance().currentUser)


    private val _screenState: MutableState<LoginRegistrationScreenState> = mutableStateOf(
        LoginRegistrationScreenState.LogIn)
    val screenState: State<LoginRegistrationScreenState> = _screenState


    private val _email: MutableState<String> = mutableStateOf("")
    val email: State<String> = _email

    private val _password: MutableState<String> = mutableStateOf("")
    val password: State<String> = _password

    private val _confirmPassword: MutableState<String> = mutableStateOf("")
    val confirmPassword: State<String> = _confirmPassword


    init {
        FirebaseAuth.getInstance().addAuthStateListener { _user.value = it.currentUser }
        changeScreenState(null)
    }


    //creates dog list in firestore
    //only used the first time someone signs up
    private fun createDogList(user: FirebaseUser?) {
        if (_user.value != null) {
            uc.createDogListFBUseCase(userId = user!!.uid)
        }
    }


    //changes the Login registration screen state
    fun changeScreenState(state: LoginRegistrationScreenState?) {
        viewModelScope.launch {
            if (_user.value != null) {
                _screenState.value = LoginRegistrationScreenState.LoggedIn


            } else {
                _screenState.value = state ?: LoginRegistrationScreenState.LogIn
                _invalidInputsState.value = InvalidInputState(visibility = false)
            }


        }
    }

    fun changeEmail(newValue: String) {
        _email.value = newValue
    }

    fun changePassword(newValue: String) {
        viewModelScope.launch {
            _password.value = newValue
        }
    }

    fun changeConfirmPassword(newValue: String) {
        viewModelScope.launch {
            _confirmPassword.value = newValue
        }
    }

    //signs up using firestore
    //if  succesful it creates a dog list and changes the scren state
    //if not it throws an exception message which is then revealed in the screen
    fun signUpEmail() {
        viewModelScope.launch {
            if (password.value == confirmPassword.value) {
                try {
                    uc.signUpUseCase(
                        email = email.value,
                        password = password.value
                    ).addOnSuccessListener {
                        Log.d(TAG, "Signup was a success $it")
                        createDogList(_user.value)
                        _screenState.value = LoginRegistrationScreenState.LoggedIn
                    }.addOnFailureListener {
                        try {
                            throw it
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            _invalidInputsState.value = InvalidInputState(
                                message = "Weak password please try again \n     -Use letters and numbers" +
                                        "\n     -Use At least 8 characters ",
                                visibility = true
                            )
                        } catch (e: FirebaseAuthUserCollisionException) {
                            _invalidInputsState.value = InvalidInputState(
                                message = "Email is already in use",
                                visibility = true
                            )
                        } catch (e: Exception) {
                            _invalidInputsState.value =
                                InvalidInputState(message = e.message!!, visibility = true)
                            Log.e(TAG, e.message!!)
                        }
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "$e error")
                }
            } else {
                _invalidInputsState.value =
                    InvalidInputState(message = "Passwords do not match", visibility = true)
                Log.d(TAG, "Error passwords: ${password.value}, ${confirmPassword.value}")
            }

        }
    }

    //Logs in using firestore
    //if failed it shows a message in the screen
    fun logInEmail() {
        viewModelScope.launch {
            if (email.value != "" && password.value != "") {
                uc.loginUseCase(
                    email = email.value,
                    password = password.value
                ).addOnSuccessListener {
                    _screenState.value = LoginRegistrationScreenState.LoggedIn
                }.addOnFailureListener {
                    try {
                        throw it
                    } catch (e: FirebaseAuthInvalidUserException) {
                        _invalidInputsState.value = InvalidInputState(
                            message = "User couldn't be found",
                            visibility = true
                        )
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        _invalidInputsState.value = InvalidInputState(
                            message = "Invalid Email or Password",
                            visibility = true
                        )
                    } catch (e: Exception) {
                        _invalidInputsState.value =
                            InvalidInputState(message = e.message!!, visibility = true)
                        Log.e(TAG, e.message!!)
                    }

                }


            }
        }

    }

    fun signOut() {
        viewModelScope.launch {
            try {
                uc.signOutUseCase()
                _screenState.value = LoginRegistrationScreenState.LogIn
            } catch (e: Exception) {
                Log.d(TAG, "Couldn't Sign Out")
            }
        }
    }

}