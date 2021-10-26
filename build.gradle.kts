import com.osacky.doctor.DoctorExtension

plugins {
    id("com.osacky.doctor") version "0.7.3"
    id("com.github.ben-manes.versions") version "0.39.0"
}

configure<DoctorExtension> {
    disallowCleanTaskDependencies.set(false)
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("com.android.tools.build:gradle:7.0.3")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}