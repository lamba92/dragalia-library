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

        val klockVersion: String by project

        @Suppress("UNUSED_VARIABLE") val commonMain by getting {
            dependencies {
                api(kotlin("stdlib-common"))
                api("com.soywiz.korlibs.klock:klock:$klockVersion")
            }
        }

        @Suppress("UNUSED_VARIABLE") val jvmMain by getting {
            dependencies {
                api(kotlin("stdlib-jdk8"))
                api("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
            }
        }

        @Suppress("UNUSED_VARIABLE") val jsMain by getting {
            dependencies {
                api(kotlin("stdlib-js"))
                api("com.soywiz.korlibs.klock:klock-js:$klockVersion")
            }
        }

        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }

    }

}

fun property(propertyName: String): String =
    project.findProperty(propertyName) as String? ?: System.getenv(propertyName) as String

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