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
//includeBuild("../kotlin-multiplatform-coroutines-runtest") {
//    dependencySubstitution {
//        substitute(module("com.github.lamba92:kotlin-multiplatform-coroutines-runtest-metadata")).with(project(":"))
//        substitute(module("com.github.lamba92:kotlin-multiplatform-coroutines-runtest-jvm")).with(project(":"))
//        substitute(module("com.github.lamba92:kotlin-multiplatform-coroutines-runtest-js")).with(project(":"))
//        substitute(module("com.github.lamba92:kotlin-multiplatform-coroutines-runtest")).with(project(":"))
//    }
//}

enableFeaturePreview("GRADLE_METADATA")
