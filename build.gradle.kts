import com.github.lamba92.dragalialost.build.`TRAVIS-TAG`

plugins {
    id("dragalia-gradle-plugin") apply false
}

allprojects {

    repositories {
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://kotlin.bintray.com/kotlinx")
        mavenCentral()
    }

    group = "com.github.lamba92"
    version = `TRAVIS-TAG` ?: "1.0.7"

}
