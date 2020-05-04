import com.github.lamba92.gradle.utils.api
import com.github.lamba92.gradle.utils.ktor

plugins {
    id("dragalia-gradle-plugin")
}

kotlin {

    val ktorVersion: String by project
    val kMongoVersion: String by project
    val napierVersion: String by project

    sourceSets["commonMain"].dependencies {
        api(project(":domain"))
        api(ktor("http", ktorVersion))
        api(ktor("utils", ktorVersion))
        api("com.github.aakira", "napier", napierVersion)
    }

    sourceSets["jvmMain"].dependencies {
        api(project(":domain"))
        api(ktor("http-jvm", ktorVersion))
        api(ktor("utils-jvm", ktorVersion))
        api(kotlin("reflect"))
        api("org.litote.kmongo", "kmongo-coroutine-serialization", kMongoVersion)
        api("com.github.aakira", "napier-jvm", napierVersion)
    }

    sourceSets["jsMain"].dependencies {
        api(project(":domain"))
        api(ktor("http-js", ktorVersion))
        api(ktor("utils-js", ktorVersion))
        api("com.github.aakira", "napier-js", napierVersion)
    }

}
