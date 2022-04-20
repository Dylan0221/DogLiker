package com.dylan0221.dogliker.presentation.viewmodels.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dylan0221.dogliker.domain.model.DogItem
import com.dylan0221.dogliker.domain.use_cases.use_cases_pack.HomeScreenUCPack
import com.dylan0221.dogliker.presentation.states.DogItemState
import com.dylan0221.dogliker.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val uc: HomeScreenUCPack,
) : ViewModel() {

    private val user = FirebaseAuth.getInstance().currentUser

    private val _cardImageVisibility: MutableState<Boolean> = mutableStateOf(true)
    val cardImageAnimationState: State<Boolean> = _cardImageVisibility

    private val _dogItem: MutableState<DogItemState> = mutableStateOf(DogItemState())
    val dogItem: State<DogItemState> = _dogItem


    init {
        getDog()

        Log.d(TAG, "Initializing... current user: ${user?.email ?: "null"}")
    }

    //Changes the DogItem Variable
    fun changeDog() {
        viewModelScope.launch {
            _cardImageVisibility.value = false
            getDog()
            _cardImageVisibility.value = true
        }
    }

    // Gets dog from the Dog api
    @VisibleForTesting
    private fun getDog() {
        viewModelScope.launch {
            uc.getDogDtoUseCase().collect { value ->
                when (value) {
                    is Resource.Error -> {
                        _dogItem.value = DogItemState(error = value.message ?: "Unknown error")
                    }
                    is Resource.Loading -> {
                        _dogItem.value = DogItemState(loading = true)
                        Log.d(TAG, "getDog Resource Loading")
                    }
                    is Resource.Success -> {
                        _dogItem.value = DogItemState(success = value.data?.toDogItem())
                        Log.d(TAG, "getDog Resource Success: ${dogItem.value.success?.image}")
                    }
                }
            }
        }
    }

    // sends dog to the firestore database
    fun likeDog() {
        val dogItem = dogItem.value.success
        if (dogItem!!.isLiked != true) {
            _dogItem.value = DogItemState(success = DogItem(
                id = dogItem.id,
                image = dogItem.image,
                status = dogItem.status,
                isLiked = true
            ))
        }
        if (user != null && _dogItem.value.success != null) {
            uc.addDogItemFBUseCase(user.uid, _dogItem.value.success!!)
        }
    }

}