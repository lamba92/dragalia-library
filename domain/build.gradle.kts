import com.github.lamba92.dragalialost.build.kotlinx

plugins {
    id("dragalia-gradle-plugin")
}

kotlin {

    val klockVersion: String by project
    val coroutinesVersion: String by project

    sourceSets["commonMain"].dependencies {
        api(kotlin("stdlib-common"))
        api("com.soywiz.korlibs.klock:klock:$klockVersion")
        api(kotlinx("coroutines-core-common", coroutinesVersion))
    }

    sourceSets["commonTest"].dependencies {
        api(kotlin("test-annotations-common"))
        api(kotlin("test-common"))
    }

    sourceSets["jvmMain"].dependencies {
        api(kotlin("stdlib-jdk8"))
        api("com.soywiz.korlibs.klock:klock-jvm:$klockVersion")
        api(kotlinx("coroutines-core", coroutinesVersion))
    }

    sourceSets["jvmTest"].dependencies {
        api(kotlin("test-junit"))
    }

    sourceSets["jsMain"].dependencies {
        api(kotlin("stdlib-js"))
        api("com.soywiz.korlibs.klock:klock-js:$klockVersion")
        api(kotlinx("coroutines-core-js", coroutinesVersion))
        implementation(npm("rxjs"))
        implementation(npm("left-pad"))
    }

    sourceSets["jsTest"].dependencies {
        api(kotlin("test-js"))
    }

}
