val kotlinVersion: String by settings

pluginManagement {
    repositories {
        jcenter()
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm", "org.jetbrains.kotlin.js", "org.jetbrains.kotlin.multiplatform", "org.jetbrains.kotlin.android", "org.jetbrains.kotlin.android.extensions" ->
                    useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
                "org.jetbrains.kotlin.plugin.serialization" -> useModule("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
            }
        }
    }
}



rootProject.name = "dragalia-library"
include(":domain", ":data", ":core", "kodein-di")

enableFeaturePreview("GRADLE_METADATA")
