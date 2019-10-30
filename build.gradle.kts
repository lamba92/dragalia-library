plugins {
    kotlin("multiplatform") apply false
}

allprojects {
    group = "com.github.lamba92"
    version = "1.0.4"

    extensions.findByName("buildScan")?.withGroovyBuilder {
        setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
        setProperty("termsOfServiceAgree", "yes")
    }
}

fun property_(propertyName: String): String? =
    project.findProperty(propertyName) as String? ?: System.getenv(propertyName)

subprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://maven.pkg.github.com/${property("githubAccount")}/${rootProject.name}") {
            name = "GitHubPackages"
            credentials {
                username = property_("githubAccount")
                password = property_("githubToken")
            }
        }
    }
}

tasks.register<Delete>("turboClean") {
    group = "clean"
    allprojects {
        delete(buildDir)
    }
}

//val nodePackagesCopyTask by tasks.register<Copy>("copyNodePackagesFromSubprojects") {
//    evaluationDependsOnChildren()
//    into(file("$buildDir/nodePackages"))
//    subprojects {
//        if ("buildNodePackage" in tasks.map { it.name }) {
//            val t by tasks.named<Copy>("buildNodePackage")
//            dependsOn(t)
//            from(t.destinationDir) {
//                into("${rootProject.name}-$name")
//            }
//        }
//    }
//}

tasks.register<Zip>("zipNodePackages") {
    dependsOn(tasks.named("build"))
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