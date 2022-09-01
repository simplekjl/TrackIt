object Compose {

    private const val composeVersion = "1.2.1"
    private const val lifecycleViewModelVersion = "2.4.1"
    private const val navigationComposeVersion = "2.5.1"
    private const val hiltNavigationComposeVersion = "1.0.0"
    private const val testComposeVersion = "1.2.1"

    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleViewModelVersion"
    const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:$composeVersion"
    const val navigation = "androidx.navigation:navigation-compose:$navigationComposeVersion"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
    const val testJunit = "androidx.compose.ui:ui-test-junit4:$testComposeVersion"
    const val testManifest = "androidx.compose.ui:ui-test-manifest:$testComposeVersion"
}