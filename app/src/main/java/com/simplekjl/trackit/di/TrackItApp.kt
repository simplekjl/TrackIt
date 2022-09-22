package com.simplekjl.trackit.di

import android.app.Application
import com.simplekjl.data.di.dataModule
import com.simplekjl.domain.di.domainModule
import com.simplekjl.trackit.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class TrackItApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (isReleaseBuild) Level.ERROR else Level.NONE)
            androidContext(this@TrackItApp)
            modules(
                listOf(
                    mainModule,
                    dataModule,
                    domainModule
                )
            )
        }
    }

    companion object {
        val isReleaseBuild: Boolean
            get() = isReleaseBuild(BuildConfig.BUILD_TYPE)
    }
}

fun isReleaseBuild(buildType: String): Boolean {
    return buildType.equals(other = "release", ignoreCase = true)
}
