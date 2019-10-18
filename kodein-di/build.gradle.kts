import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform")
    `maven-publish`
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
                moduleKind = "umd"
            }
        }
        browser()
        nodejs()
        useCommonJs()
    }

    sourceSets {

        val kodeinVersion: String by project
        val ktorVersion: String by project


        @Suppress("UNUSED_VARIABLE") val commonMain by getting {
            dependencies {
                api(project(":core"))
                api(kodein("core", kodeinVersion))
                api(kodein("erased", kodeinVersion))
                api(ktor("client-serialization", ktorVersion))
            }
        }

        @Suppress("UNUSED_VARIABLE") val jvmMain by getting {
            dependencies {
                api(ktor("client-okhttp", ktorVersion))
                api(ktor("client-serialization-jvm", ktorVersion))
            }
        }

        @Suppress("UNUSED_VARIABLE") val jsMain by getting {
            dependencies {
                api(ktor("client-js", ktorVersion))
                api(ktor("client-serialization-js", ktorVersion))
            }
        }

        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }

    }
}

fun property(propertyName: String): String? =
    project.findProperty(propertyName) as String? ?: System.getenv(propertyName)

publishing {
    repositories {
        maven("https://maven.pkg.github.com/${property("githubAccount")}/${rootProject.name}") {
            name = "GitHubPackages"
            credentials {
                username = property("githubAccount")
                password = property("githubToken")
            }
        }
    }
}

@Suppress("unused")
fun KotlinDependencyHandler.kodein(module: String, version: String? = null): Any =
    "org.kodein.di:kodein-di-$module${version?.let { ":$version" } ?: ""}"

@Suppress("unused")
fun KotlinDependencyHandler.ktor(module: String, version: String? = null): Any =
    "io.ktor:ktor-$module${version?.let { ":$version" } ?: ""}"