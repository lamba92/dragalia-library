import com.github.lamba92.dragalialost.build.ktor

plugins {
    id("dragalia-gradle-plugin")
}

kotlin {

    val ktorVersion: String by project
    val textEncodingVersion: String by project

    sourceSets["commonMain"].dependencies {
        api(project(":data"))
        api(ktor("client-core", ktorVersion))
    }

    sourceSets["jvmMain"].dependencies {
        api(project(":data"))
        api(ktor("client-core-jvm", ktorVersion))
    }

    sourceSets["jvmTest"].dependencies {
        api(kotlin("test-junit"))
    }

    sourceSets["jsMain"].dependencies {
        api(project(":data"))
        api(ktor("client-core-js", ktorVersion))
        api(npm("text-encoding", textEncodingVersion))
    }

}