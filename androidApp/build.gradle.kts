plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test:core-ktx:1.3.0")
    androidTestImplementation("org.awaitility:awaitility:4.1.0") {
        exclude(group = "org.hamcrest")
    }
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
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
            buildConfigField("String", "HOSTNAME", "\"http://192.168.1.7:1313/\"")
            addManifestPlaceholders(mapOf("clearText" to false))
            isMinifyEnabled = false
        }

        create("dev") {
            initWith(debug)
            buildConfigField("String", "HOSTNAME", "\"http://192.168.1.5:1313/\"")
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}