package com.dylan0221.dogliker5.presentation.viewmodels.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dylan0221.dogliker5.domain.model.DogItem
import com.dylan0221.dogliker5.domain.use_cases.use_cases_pack.SavedDogsScreenUCPack
import com.dylan0221.dogliker5.presentation.states.DogItemState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedDogsViewModel @Inject constructor(
    private val uc: SavedDogsScreenUCPack,
) : ViewModel() {

    private val currentUserid = FirebaseAuth.getInstance().currentUser?.uid

    private val _dogs: MutableState<List<DogItemState>> = mutableStateOf(emptyList())
    val dogs: State<List<DogItemState>> = _dogs

    init {
        getLikedDogs()
    }


    private fun getLikedDogs() {
        if (currentUserid != null) {
            try {

                uc.getAllDogItemFBUseCase(currentUserid).addOnSuccessListener {
                    val dogList = it.get("LikedDogs") as List<String>

                    val dogStateItem = dogList.map {
                        DogItemState(success = DogItem(image = it, isLiked = true, status = ""))
                    }

                    _dogs.value = dogStateItem

                    Log.d(TAG, "firebase result:  " + dogs.value.toString())
                }
            } catch (e: Exception) {
                Log.d(TAG, "getLikedDogs error")

            }
        }
    }


}