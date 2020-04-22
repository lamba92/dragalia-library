import com.github.lamba92.gradle.utils.api
import com.github.lamba92.gradle.utils.kotlinx
import com.github.lamba92.gradle.utils.ktor

plugins {
    id("dragalia-gradle-plugin")
    kotlin("plugin.serialization")
}

kotlin {

    val ktorVersion: String by project
    val textEncodingVersion: String by project
    val kMongoVersion: String by project
    val atomicfuVersion: String by project

    sourceSets["commonMain"].dependencies {
        api(project(":data"))
        api(ktor("client-core", ktorVersion))
        api(kotlinx("atomicfu-common", atomicfuVersion, false))
    }

    sourceSets["jvmMain"].dependencies {
        api(project(":data"))
        api(ktor("client-core-jvm", ktorVersion))
        api(kotlinx("atomicfu-common", atomicfuVersion, false))
        api("org.litote.kmongo", "kmongo-coroutine-serialization", kMongoVersion)
    }

    sourceSets["jvmTest"].dependencies {
        api(kotlin("test-junit"))
    }

    sourceSets["jsMain"].dependencies {
        api(project(":data"))
        api(ktor("client-core-js", ktorVersion))
        api(npm("text-encoding", textEncodingVersion))
        api(kotlinx("atomicfu-common", atomicfuVersion, false))
    }

}
