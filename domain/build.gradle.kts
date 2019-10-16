plugins {
    kotlin("multiplatform")
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