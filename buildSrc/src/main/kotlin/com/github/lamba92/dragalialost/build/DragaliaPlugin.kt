package com.github.lamba92.dragalialost.build

import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.named
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("unused")
class DragaliaPlugin : Plugin<Project> {

    private val kotlinVersion = "1.3.50"
    private val bintrayVersion = "1.8.4"

    override fun apply(target: Project) = with(target) {

        plugins.apply("org.jetbrains.kotlin.multiplatform")
        plugins.apply("org.gradle.maven-publish")
        plugins.apply("com.jfrog.bintray")

        extensions.configure<KotlinMultiplatformExtension> {
            metadata {
                mavenPublication {
                    artifactId = "${rootProject.name}-${project.name}-metadata"
                }
            }

            jvm {
                compilations.all {
                    kotlinOptions {
                        jvmTarget = "1.8"
                    }
                }
                mavenPublication {
                    artifactId = "${rootProject.name}-${project.name}-jvm"
                }
            }

            js {
                mavenPublication {
                    artifactId = "${rootProject.name}-${project.name}-js"
                }
            }

            sourceSets.all {
                languageSettings.enableLanguageFeature("InlineClasses")
            }

        }

        extensions.configure<PublishingExtension> {
            publications.named<MavenPublication>("kotlinMultiplatform") {
                artifactId = "${rootProject.name}-${project.name}"
            }
        }

        extensions.configure<BintrayExtension> {
            user = searchPropertyOrNull("bintrayUsername")
            key = searchPropertyOrNull("bintrayApiKey")
            pkg {
                version {
                    name = project.version.toString()
                }
                repo = "com.github.lamba92"
                name = "dragalia-library"
                setLicenses("Apache-2.0")
                vcsUrl = "https://github.com/lamba92/dragalia-library"
                issueTrackerUrl = "https://github.com/lamba92/dragalia-library/issues"
            }
            publish = true
            setPublications("jvm", "js", "kotlinMultiplatform")
        }

    }

}