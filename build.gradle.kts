plugins {
    id("dragalia-gradle-plugin") apply false
}

allprojects {

    repositories {
        jcenter()
        maven("https://dl.bintray.com/lamba92/com.github.lamba92")
        mavenCentral()
    }

    group = "com.github.lamba92"
    version = System.getenv("TRAVIS_TAG") ?: "1.0.7"

}

tasks.register<Zip>("zipNodePackages") {
    subprojects {
        dependsOn(tasks.named("build"))
    }
    from("$buildDir/js") {
        include("*")
        include("**/*")
        exclude("node_modules")
        exclude("node_modules.state")
        exclude("yarn.lock")
        exclude("*/.visited")

    }
    archiveBaseName.set("node_package")
    destinationDirectory.set(buildDir)
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}