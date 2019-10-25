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

        val ktorVersion: String by project
        val textEncodingVersion: String by project

        @Suppress("UNUSED_VARIABLE") val commonMain by getting {
            dependencies {
                api(project(":data"))
                api(ktor("client-core", ktorVersion))
            }
        }

        @Suppress("UNUSED_VARIABLE") val jvmMain by getting {
            dependencies {
                api(project(":data"))
                api(ktor("client-core-jvm", ktorVersion))
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
                api(ktor("client-core-js", ktorVersion))
                api(npm("text-encoding", textEncodingVersion))
            }
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
//
//tasks.register<Copy>("buildNodePackage") {
//    group = "nodejs"
//    val jsJar by tasks.named<Jar>("jsJar")
//    val jsPackageJson by tasks.named<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinPackageJsonTask>("jsPackageJson")
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

@Suppress("unused")
fun KotlinDependencyHandler.ktor(module: String, version: String? = null): Any =
    "io.ktor:ktor-$module${version?.let { ":$version" } ?: ""}"