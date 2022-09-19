package com.simplekjl.trackit

import android.app.Application
import androidx.room.Room
import com.simplekjl.trackit.framework.WeightDao
import com.simplekjl.trackit.framework.WeightDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class TrackItApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (isReleaseBuild) Level.ERROR else Level.NONE)
            androidContext(this@TrackItApp)
            modules(
                listOf(
                    databaseModule
                )
            )
        }
    }

    companion object {
        val isReleaseBuild: Boolean
            get() = isReleaseBuild(BuildConfig.BUILD_TYPE)
    }
}

val databaseModule = module {
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
}

fun isReleaseBuild(buildType: String): Boolean {
    return buildType.equals(other = "release", ignoreCase = true)
}
