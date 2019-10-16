plugins {
    kotlin("multiplatform") apply false
}

allprojects {
    group = "com.github.lamba92"
    version = "0.0.1-alpha"
}

subprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

tasks.register<Delete>("turboClean") {
    allprojects {
        delete(buildDir)
    }
}
