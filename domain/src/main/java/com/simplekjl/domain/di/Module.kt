package com.simplekjl.domain.di

import com.simplekjl.domain.usecase.NewWeightUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val domainModule = module {
    factory { NewWeightUseCase(Dispatchers.IO, get()) }
}
