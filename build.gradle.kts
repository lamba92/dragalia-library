plugins {
    kotlin("multiplatform") apply false
}

allprojects {
    group = "com.github.lamba92"
    version = "1.0-SNAPSHOT"
}

subprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}