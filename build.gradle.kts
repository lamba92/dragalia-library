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
    group = "clean"
    allprojects {
        delete(buildDir)
    }
}

val nodePackagesCopyTask by tasks.register<Copy>("copyNodePackagesFromSubprojects") {
    evaluationDependsOnChildren()
    into(file("$buildDir/nodePackages"))
    subprojects {
        if ("buildNodePackage" in tasks.map { it.name }) {
            val t by tasks.named<Copy>("buildNodePackage")
            dependsOn(t)
            from(t.destinationDir) {
                into("${rootProject.name}-$name")
            }
        }
    }
}

tasks.register<Zip>("zipNodePackages") {
    dependsOn(nodePackagesCopyTask)
    from(nodePackagesCopyTask.destinationDir)
    include("*")
    include("*/*")
    archiveBaseName.set("zipNodePackages")
    destinationDirectory.set(buildDir)
}
