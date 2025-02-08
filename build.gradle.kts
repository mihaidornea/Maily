buildscript {
    repositories {
        google()
        maven { url = uri("https://plugins.gradle.org/m2") }
        maven { url = uri("https://jitpack.io") }
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${CommonVersions.HILT}")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${CommonVersions.DETEKT}")
        classpath("com.android.tools.build:gradle:${CommonVersions.BUILD_GRADLE}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${CommonVersions.KOTLIN}")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version CommonVersions.DETEKT
}

allprojects {
    repositories {
        google()
        maven { url = uri("https://plugins.gradle.org/m2") }
        maven { url = uri("https://jitpack.io") }
    }

    apply(from = "$rootDir/ktlint.gradle")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        toolVersion = CommonVersions.DETEKT
        config.setFrom(file("../default-detekt-config.yml"))
        buildUponDefaultConfig = true
    }
}

tasks.create<GradleBuild>("x") {
    tasks = listOf(
        ":app:ktlintFormat", ":app:ktlint", ":app:detekt", "app:lint",
        "data:ktlintFormat", "data:ktlint", "data:detekt",
        "domain:ktlintFormat", "domain:ktlint", "domain:detekt"
    )
}