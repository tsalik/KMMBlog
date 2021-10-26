import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test:core-ktx:1.4.0")
    androidTestImplementation("org.awaitility:awaitility:4.1.1") {
        exclude(group = "org.hamcrest")
    }
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.2")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "me.tsalikis.blog.android"
        minSdk = 22
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {

        val debug = getByName("debug") {
            buildConfigField("String", "HOSTNAME", "\"http://localhost:8080/\"")
            addManifestPlaceholders(mapOf("clearText" to true))
        }

        getByName("release") {
            buildConfigField("String", "HOSTNAME", "\"https://tsalikis.blog/\"")
            addManifestPlaceholders(mapOf("clearText" to false))
            isMinifyEnabled = false
        }

        create("dev") {
            initWith(debug)
            val hostname = gradleLocalProperties(rootDir).getProperty("hostname")
            val hostNameExistsInLocalProperties = hostname != null
            if (hostNameExistsInLocalProperties) {
                buildConfigField("String", "HOSTNAME", hostname)
            }
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}