import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

kotlin {

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    js {
        compilations.all {
            kotlinOptions {
                moduleKind = "commonjs"
            }
        }
    }

    sourceSets {

        val ktorVersion: String by project
        val kotlinxSerializationVersion: String by project

        @Suppress("UNUSED_VARIABLE") val commonMain by getting {
            dependencies {
                api(project(":domain"))
                api(ktor("http", ktorVersion))
                api(ktor("utils", ktorVersion))
                api(serialization("runtime-common", kotlinxSerializationVersion))

            }
        }

        @Suppress("UNUSED_VARIABLE") val jvmMain by getting {
            dependencies {
                api(project(":domain"))
                api(ktor("http-jvm", ktorVersion))
                api(ktor("utils-jvm", ktorVersion))
                api(serialization("runtime", kotlinxSerializationVersion))
                api(kotlin("reflect"))
            }
        }

        @Suppress("UNUSED_VARIABLE") val jsMain by getting {
            dependencies {
                api(project(":domain"))
                api(ktor("http-js", ktorVersion))
                api(ktor("utils-js", ktorVersion))
                api(serialization("runtime-js", kotlinxSerializationVersion))
            }
        }

    }
}

@Suppress("unused")
fun KotlinDependencyHandler.ktor(module: String, version: String? = null): Any =
    "io.ktor:ktor-$module${version?.let { ":$version" } ?: ""}"

@Suppress("unused")
fun KotlinDependencyHandler.serialization(module: String, version: String? = null): Any =
    "org.jetbrains.kotlinx:kotlinx-serialization-$module${version?.let { ":$version" } ?: ""}"