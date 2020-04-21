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
    google()
    maven("https://plugins.gradle.org/m2/")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://kotlin.bintray.com/kotlinx")
    maven("https://dl.bintray.com/lamba92/com.github.lamba92")
}

dependencies {

    val kotlinVersion: String by project
    val lambaGradleUtils: String by project

    api(kotlin("stdlib-jdk8", kotlinVersion))
    api(kotlin("reflect", kotlinVersion))
    api("com.github.lamba92", "lamba-gradle-utils", lambaGradleUtils)

}
