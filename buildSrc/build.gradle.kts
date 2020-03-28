plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("dragalia-plugin") {
            id = "dragalia-gradle-plugin"
            implementationClass = "com.github.lamba92.dragalialost.build.DragaliaPlugin"
        }
    }
}

repositories {
    jcenter()
    maven("https://plugins.gradle.org/m2/")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
    val kotlinVersion: String by project
    val bintrayVersion: String by project
    api("org.jetbrains.kotlin", "kotlin-gradle-plugin", kotlinVersion)
    api("com.jfrog.bintray.gradle", "gradle-bintray-plugin", bintrayVersion)
    api("org.jetbrains.kotlin", "kotlin-serialization", kotlinVersion)
}
