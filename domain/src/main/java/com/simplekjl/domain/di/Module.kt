package com.simplekjl.domain.di

import com.simplekjl.domain.usecase.GetProfileUseCase
import com.simplekjl.domain.usecase.GetWeightsUseCase
import com.simplekjl.domain.usecase.NewWeightUseCase
import com.simplekjl.domain.usecase.UpdateWeightUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {
    factory { UpdateWeightUseCase(Dispatchers.IO, get()) }
    factory { GetWeightsUseCase(Dispatchers.IO, get()) }
    factory { NewWeightUseCase(Dispatchers.IO, get()) }
    factory { GetProfileUseCase(Dispatchers.IO, get()) }
}
