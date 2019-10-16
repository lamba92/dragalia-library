import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform")
    `maven-publish`
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        browser()
        nodejs()
        useCommonJs()
    }

    val rxjsVersion: String by project
    sourceSets {

        val ktorVersion: String by project
        val kodeinVersion: String by project
        val textEncodingVersion: String by project

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

        @Suppress("UNUSED_VARIABLE") val jvmTest by getting {
            dependencies {
                api(kotlin("test-junit"))
            }
        }

        @Suppress("UNUSED_VARIABLE") val jsMain by getting {
            dependencies {
                api(project(":data"))
                api(ktor("client-js", ktorVersion))
                api(ktor("client-serialization-js", ktorVersion))
                api(npm("rxjs", rxjsVersion))
                api(npm("text-encoding", textEncodingVersion))
            }
        }

    }

}

fun property(propertyName: String): String =
    project.property(propertyName) as String? ?: System.getenv(propertyName) as String

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

tasks.register<Copy>("buildNodePackage") {
    group = "nodejs"
    val jsJar by tasks.named<Jar>("jsJar")
    val jsPackageJson by tasks.named<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinPackageJsonTask>("jsPackageJson")
    dependsOn(jsJar, jsPackageJson)

    into(file("$buildDir/nodePackage"))

    from(jsPackageJson.packageJson)

    from(zipTree(jsJar.archiveFile)) {
        include("*.js")
        into("kotlin")
    }

}

@Suppress("unused")
fun KotlinDependencyHandler.ktor(module: String, version: String? = null): Any =
    "io.ktor:ktor-$module${version?.let { ":$version" } ?: ""}"

@Suppress("unused")
fun KotlinDependencyHandler.kodein(module: String, version: String? = null): Any =
    "org.kodein.di:kodein-di-$module${version?.let { ":$version" } ?: ""}"