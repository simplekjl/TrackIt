object Google {

    private const val materialVersion = "1.4.0"
    private const val daggerVersion = "2.42"
    private const val hiltVersion = "2.38.1"
    private const val mapsVersion = "18.1.0"
    private const val mapsUtilsVersion = "2.2.0"
    private const val nav_version = "2.5.1"
    private const val playServicesVersion ="20.0.0"

    const val material = "com.google.android.material:material:$materialVersion"
    const val daggerCore = "com.google.dagger:dagger:$daggerVersion"
    const val daggerAndroid = "com.google.dagger:dagger-android:$daggerVersion"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$daggerVersion"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val hiltClasspath = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"

    // MAPS
    const val maps = "com.google.android.gms:play-services-maps:$mapsVersion"
    const val mapsUtils = "'com.google.maps.android:android-maps-utils:$mapsUtilsVersion"
    // navigation
    const val navigation = "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"

    //Services
    const val playServices = "com.google.android.gms:play-services-location:$playServicesVersion"

}