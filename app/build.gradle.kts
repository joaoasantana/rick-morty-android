plugins {
    alias(libs.plugins.android.application)

    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.joaoasantana.rickandmorty"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.joaoasantana.rickandmorty"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.activity.compose)

    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    implementation(platform(libs.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)

    implementation(libs.lifecycle.compose)

    implementation(libs.navigation.compose)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    implementation(libs.room)
    implementation(libs.room.compiler)
    implementation(libs.room.runtime)

    testImplementation(libs.junit)

    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)

    androidTestImplementation(libs.androidx.junit)
}
