import com.github.lamba92.gradle.utils.kotlinx
import com.github.lamba92.gradle.utils.serialization

plugins {
    id("dragalia-gradle-plugin")
}

kotlin {

    val klockVersion: String by project
    val coroutinesVersion: String by project
    val kotlinxSerializationVersion: String by project

    sourceSets["commonMain"].dependencies {
        api(kotlin("stdlib-common"))
        api("com.soywiz.korlibs.klock:klock:$klockVersion")
        api(kotlinx("coroutines-core-common", coroutinesVersion))
        api(serialization("runtime-common", kotlinxSerializationVersion))
    }

    sourceSets["commonTest"].dependencies {
        api(kotlin("test-annotations-common"))
        api(kotlin("test-common"))
    }

    sourceSets["jvmMain"].dependencies {
        api(kotlin("stdlib-jdk8"))
        api("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
        api(kotlinx("coroutines-core", coroutinesVersion))
        api(serialization("runtime", kotlinxSerializationVersion))
    }

    sourceSets["jvmTest"].dependencies {
        api(kotlin("test-junit"))
    }

    sourceSets["jsMain"].dependencies {
        api(kotlin("stdlib-js"))
        api("com.soywiz.korlibs.klock:klock-js:$klockVersion")
        api(kotlinx("coroutines-core-js", coroutinesVersion))
        api(serialization("runtime-js", kotlinxSerializationVersion))
    }

    sourceSets["jsTest"].dependencies {
        api(kotlin("test-js"))
    }

}
