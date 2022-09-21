package com.simplekjl.trackit

import android.app.Application
import com.simplekjl.data.di.dataModule
import com.simplekjl.trackit.di.mainModule
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
                    dataModule
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
