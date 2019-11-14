import com.github.lamba92.dragalialost.build.ktor
import com.github.lamba92.dragalialost.build.serialization

plugins {
    id("dragalia-gradle-plugin")
}

kotlin {

    val ktorVersion: String by project
    val kotlinxSerializationVersion: String by project

    sourceSets["commonMain"].dependencies {
        api(project(":domain"))
        api(ktor("http", ktorVersion))
        api(ktor("utils", ktorVersion))
        api(serialization("runtime-common", kotlinxSerializationVersion))
    }

    sourceSets["jvmMain"].dependencies {
        api(project(":domain"))
        api(ktor("http-jvm", ktorVersion))
        api(ktor("utils-jvm", ktorVersion))
        api(serialization("runtime", kotlinxSerializationVersion))
        api(kotlin("reflect"))
    }

    sourceSets["jsMain"].dependencies {
        api(project(":domain"))
        api(ktor("http-js", ktorVersion))
        api(ktor("utils-js", ktorVersion))
        api(serialization("runtime-js", kotlinxSerializationVersion))
    }

}
