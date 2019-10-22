import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
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
        nodejs()
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

//tasks.register<Sync>("extractMavenJsDependencies") {
//
//
//    val jsClasses by tasks.named("jsMainClasses")
//    dependsOn(jsClasses)
//    val e = configurations["jsMainApi"].files(*configurations["jsMainApi"].dependencies.toTypedArray())
//
//    println(e)
//
//    e.forEach { file ->
//        from(zipTree(file)) {
//            includeEmptyDirs = false
//            include {
//                it.path.endsWith(".js") &&
//                        (it.path.startsWith("META-INF/resources/") || !it.path.startsWith("META-INF/"))
//            }
//        }
//    }
//
//    into("$buildDir/web")
//
//}
//
//tasks.register<Copy>("buildNodePackage") {
//
//    group = "nodejs"
//
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

@Suppress("unused")
fun KotlinDependencyHandler.ktor(module: String, version: String? = null): Any =
    "io.ktor:ktor-$module${version?.let { ":$version" } ?: ""}"

@Suppress("unused")
fun KotlinDependencyHandler.serialization(module: String, version: String? = null): Any =
    "org.jetbrains.kotlinx:kotlinx-serialization-$module${version?.let { ":$version" } ?: ""}"