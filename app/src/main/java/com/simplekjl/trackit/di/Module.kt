package com.simplekjl.trackit.di

import androidx.room.Room
import com.simplekjl.data.repository.LocalSource
import com.simplekjl.trackit.BuildConfig
import com.simplekjl.trackit.framework.LocalSourceImpl
import com.simplekjl.trackit.framework.database.WeightDao
import com.simplekjl.trackit.framework.database.WeightDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            WeightDatabase::class.java,
            BuildConfig.DB_NAME
        ).build()
    }
    single<WeightDao> {
        val database = get<WeightDatabase>()
        database.getWeightDao()
    }
    factory<LocalSource> { LocalSourceImpl(get()) }
}
