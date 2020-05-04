import com.github.lamba92.gradle.utils.TRAVIS_TAG

plugins {
    id("dragalia-gradle-plugin") apply false
}

allprojects {

    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://kotlin.bintray.com/kotlinx")
        mavenCentral()
    }

    group = "com.github.lamba92"
    version = TRAVIS_TAG ?: "1.0.7"

}
