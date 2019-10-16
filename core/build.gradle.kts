import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js()

    sourceSets {

        val ktorVersion: String by project
        val kodeinVersion: String by project

        @Suppress("UNUSED_VARIABLE") val commonMain by getting {
            dependencies {
                api(project(":data"))
                api(ktor("client-core", ktorVersion))
                api(kodein("core", kodeinVersion))
                api(kodein("erased", kodeinVersion))
            }
        }

        @Suppress("UNUSED_VARIABLE") val jvmMain by getting {
            dependencies {
                api(project(":data"))
                api(ktor("client-okhttp", ktorVersion))
                api(ktor("client-apache", ktorVersion))
                api(ktor("client-json-jvm", ktorVersion))
                api(ktor("client-serialization-jvm", ktorVersion))
                api(ktor("client-gson", ktorVersion))
                api(ktor("client-logging-jvm", ktorVersion))
                runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
            }
        }

        @Suppress("UNUSED_VARIABLE") val jsMain by getting {
            dependencies {
                api(project(":data"))
                api(ktor("client-js", ktorVersion))
                api(ktor("client-serialization-js", ktorVersion))
            }
        }

    }
}


@Suppress("unused")
fun KotlinDependencyHandler.ktor(module: String, version: String? = null): Any =
    "io.ktor:ktor-$module${version?.let { ":$version" } ?: ""}"

@Suppress("unused")
fun KotlinDependencyHandler.kodein(module: String, version: String? = null): Any =
    "org.kodein.di:kodein-di-$module${version?.let { ":$version" } ?: ""}"