package com.simplekjl.data.di

import com.simplekjl.data.repository.RepositoryImpl
import com.simplekjl.domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {
    factory<Repository> { RepositoryImpl(get()) }
}
