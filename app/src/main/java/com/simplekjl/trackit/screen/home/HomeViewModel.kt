package com.simplekjl.trackit.screen.home

import androidx.lifecycle.ViewModel
import com.simplekjl.domain.usecase.GetWeightsUseCase
import com.simplekjl.domain.usecase.UpdateWeightUseCase

// get first weight in the list of weights
// get last available weight
// calculate current weight

class HomeViewModel(
    private val updateWeightUseCase: UpdateWeightUseCase,
    private val getWeightsUseCase: GetWeightsUseCase
) : ViewModel() {

}