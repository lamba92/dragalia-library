@file:Suppress("unused")

package com.github.lamba92.dragalialost.build

import com.jfrog.bintray.gradle.BintrayExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.closureOf
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun KotlinDependencyHandler.kodein(module: String, version: String? = null): Any =
    "org.kodein.di:kodein-di-$module${version?.let { ":$version" } ?: ""}"

fun KotlinDependencyHandler.ktor(module: String, version: String? = null): Any =
    "io.ktor:ktor-$module${version?.let { ":$version" } ?: ""}"

fun KotlinDependencyHandler.lamba(module: String, version: String? = null): Any =
    "com.github.lamba92:$module${version?.let { ":$version" } ?: ""}"

fun Project.searchPropertyOrNull(propertyName: String): String? =
    findProperty(propertyName) as String? ?: System.getenv(propertyName)

fun BintrayExtension.pkg(action: BintrayExtension.PackageConfig.() -> Unit) {
    pkg(closureOf(action))
}

fun BintrayExtension.PackageConfig.version(action: BintrayExtension.VersionConfig.() -> Unit) {
    version(closureOf(action))
}

fun KotlinDependencyHandler.kotlinx(module: String, version: String? = null): Any =
    "org.jetbrains.kotlinx:kotlinx-$module${version?.let { ":$version" } ?: ""}"

fun KotlinDependencyHandler.serialization(module: String, version: String? = null): Any =
    "org.jetbrains.kotlinx:kotlinx-serialization-$module${version?.let { ":$version" } ?: ""}"