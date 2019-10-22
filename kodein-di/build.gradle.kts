@file:Suppress("UNUSED_VARIABLE")

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
            nodejs()
        }
    }

    sourceSets {

        val kodeinVersion: String by project
        val ktorVersion: String by project
        val coroutineRunTestVersion: String by project
        val logbackVersion: String by project
        val utf8ValidateVersion: String by project
        val bufferutilVersion: String by project
        val nodeFetchVersion: String by project

        val commonMain by getting {
            dependencies {
                api(project(":core"))
                api(kodein("core", kodeinVersion))
                api(kodein("erased", kodeinVersion))
                api(ktor("client-serialization", ktorVersion))
                api(ktor("client-logging", ktorVersion))
            }
        }

        val commonTest by getting {
            dependencies {
                api(lamba("kotlin-multiplatform-coroutines-runtest", coroutineRunTestVersion))
                api(kotlin("test-annotations-common"))
                api(kotlin("test-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                api(ktor("client-okhttp", ktorVersion))
                api(ktor("client-serialization-jvm", ktorVersion))
                api(ktor("client-logging-jvm", ktorVersion))
                api("ch.qos.logback:logback-classic:$logbackVersion")
            }
        }

        val jvmTest by getting {
            dependencies {
                api(kotlin("test-junit"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(ktor("client-js", ktorVersion))
                api(ktor("client-serialization-js", ktorVersion))
                api(ktor("client-logging-js", ktorVersion))
                api(npm("utf-8-validate", utf8ValidateVersion))
                api(npm("bufferutil", bufferutilVersion))
                api(npm("node-fetch", nodeFetchVersion))
            }
        }

        val jsTest by getting {
            dependencies {
                api(kotlin("test-js"))
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

@Suppress("unused")
fun KotlinDependencyHandler.lamba(module: String, version: String? = null): Any =
    "com.github.lamba92:$module${version?.let { ":$version" } ?: ""}"