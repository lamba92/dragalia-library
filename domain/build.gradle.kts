@file:Suppress("UNUSED_VARIABLE")

import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform")
    `maven-publish`
}

kotlin {

    metadata {
        mavenPublication {
            artifactId = "${rootProject.name}-${project.name}-metadata"
        }
    }

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        mavenPublication {
            artifactId = "${rootProject.name}-${project.name}-jvm"
        }
    }

    js {
        nodejs()
        mavenPublication {
            artifactId = "${rootProject.name}-${project.name}-js"
        }
    }

    sourceSets {

        val klockVersion: String by project
        val coroutinesVersion: String by project
        val rxjsVersion: String by project

        val commonMain by getting {
            dependencies {
                api(kotlin("stdlib-common"))
                api("com.soywiz.korlibs.klock:klock:$klockVersion")
                api(kotlinx("coroutines-core-common", coroutinesVersion))
            }
        }

        val commonTest by getting {
            dependencies {
                api(kotlin("test-annotations-common"))
                api(kotlin("test-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                api(kotlin("stdlib-jdk8"))
                api("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
                api(kotlinx("coroutines-core", coroutinesVersion))
            }
        }

        val jvmTest by getting {
            dependencies {
                api(kotlin("test-junit"))
            }
        }

        val jsMain by getting {
            dependencies {
                api(kotlin("stdlib-js"))
                api("com.soywiz.korlibs.klock:klock-js:$klockVersion")
                api(kotlinx("coroutines-core-js", coroutinesVersion))
                implementation(npm("rxjs", rxjsVersion))
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
    val commonModulePublication = publications["kotlinMultiplatform"] as MavenPublication
    commonModulePublication.artifactId = "${rootProject.name}-${project.name}-common"
}

@Suppress("unused")
fun KotlinDependencyHandler.kotlinx(module: String, version: String? = null): Any =
    "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" } ?: ""}"


//tasks.register<Copy>("buildPackageJson") {
//    group = "nodejs"
//    val klockVersion: String by project
//    val kotlinVersion: String by project
//    from("package.json") {
//        filter<ReplaceTokens>(
//            "tokens" to mapOf(
//                "LIBRARY-NAME" to "${rootProject.name}-${project.name}",
//                "KOTLIN-VERSION" to kotlinVersion,
//                "LIBRARY-VERSION" to version,
//                "KLOCK-VERSION" to klockVersion
//            )
//        )
//    }
//    into(file("$buildDir/tmp"))
//}
//
//tasks.register<Copy>("buildNodePackage") {
//    group = "nodejs"
//    val jsJar by tasks.named<Jar>("jsJar")
//    val jsPackageJson by tasks.named<KotlinPackageJsonTask>("jsPackageJson")
//    dependsOn(jsJar, jsPackageJson)
//
//    into(file("$buildDir/nodePackage"))
//
//    from(jsPackageJson.packageJson)
//
//    from(zipTree(jsJar.archiveFile)) {
//        include("*.js")
//        into("kotlin")
//    }
//
//}