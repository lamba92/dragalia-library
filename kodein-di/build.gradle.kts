import com.github.lamba92.dragalialost.build.kodein
import com.github.lamba92.dragalialost.build.ktor

plugins {
    id("dragalia-gradle-plugin")
}

kotlin {

    val kodeinVersion: String by project
    val ktorVersion: String by project
    val logbackVersion: String by project

    sourceSets["commonMain"].dependencies {
        api(project(":core"))
        api(kodein("core", kodeinVersion))
        api(kodein("erased", kodeinVersion))
        api(ktor("client-serialization", ktorVersion))
        api(ktor("client-logging", ktorVersion))
    }

    sourceSets["commonTest"].dependencies {
        api(kotlin("test-annotations-common"))
        api(kotlin("test-common"))
    }

    sourceSets["jvmMain"].dependencies {
        api(ktor("client-okhttp", ktorVersion))
        api(ktor("client-serialization-jvm", ktorVersion))
        api(ktor("client-logging-jvm", ktorVersion))
        api("ch.qos.logback:logback-classic:$logbackVersion")
    }

    sourceSets["jvmTest"].dependencies {
        api(kotlin("test-junit"))
    }

    sourceSets["jsMain"].dependencies {
        api(ktor("client-js", ktorVersion))
        api(ktor("client-serialization-js", ktorVersion))
        api(ktor("client-logging-js", ktorVersion))
    }

    sourceSets["jsTest"].dependencies {
        api(kotlin("test-js"))
    }

}
