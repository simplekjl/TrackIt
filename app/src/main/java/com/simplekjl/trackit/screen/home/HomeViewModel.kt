@file:OptIn(InternalCoroutinesApi::class)

package com.simplekjl.trackit.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.simplekjl.domain.model.Profile
import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.usecase.GetProfileUseCase
import com.simplekjl.domain.usecase.GetWeightsUseCase
import com.simplekjl.domain.usecase.UpdateWeightUseCase
import com.simplekjl.domain.utils.Result
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.map

// get first weight in the list of weights
// get last available weight
// calculate current weight

class HomeViewModel(
    private val updateWeightUseCase: UpdateWeightUseCase,
    private val getProfile: GetProfileUseCase,
    private val getWeightsUseCase: GetWeightsUseCase
) : ViewModel() {
    val profile: LiveData<Profile> = getProfile(Unit).map {
        when (it) {
            is Result.Error -> {
                Profile(1, "Error", goalWeight = 12.0, 100L)
            }
            is Result.Success -> Profile(1, "Jose", goalWeight = 12.0, 100L)
        }
    }.asLiveData()
    val weights: LiveData<List<Weight>> = getWeightsUseCase(Unit).map {
        when (it) {
            is Result.Error -> {
                /*Show toast*/
                emptyList()
            }
            is Result.Success -> {
                it.data
            }
        }
    }.asLiveData()
}
