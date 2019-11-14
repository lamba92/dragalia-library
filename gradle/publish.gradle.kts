buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        val bintrayVersion: String by project
        classpath("com.jfrog.bintray.gradle", "gradle-bintray-plugin", bintrayVersion)
    }
}

fun searchProperty(name: String) =
    project.findProperty(name) as String? ?: try {
        System.getenv(name)
    } catch (e: Throwable) {
        null
    }

bintray {
    user = searchProperty("bintrayUsername")
    key = searchProperty("bintrayApiKey")
    pkg {
        version {
            name = project.version.toString()
        }
        repo = "com.github.lamba92"
        name = "krwp-solver"
        setLicenses("Apache-2.0")
        vcsUrl = "https://github.com/lamba92/krwp-solver"
        issueTrackerUrl = "https://github.com/lamba92/krwp-solver/issues"
    }
    publish = true
    setPublications("js", "jvm")
}

fun BintrayExtension.pkg(action: BintrayExtension.PackageConfig.() -> Unit) =
    pkg(closureOf(action))

fun BintrayExtension.PackageConfig.version(action: BintrayExtension.VersionConfig.() -> Unit) =
    version(closureOf(action))