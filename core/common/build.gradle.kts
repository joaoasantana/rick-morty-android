plugins {
    alias(libs.plugins.android.library)

    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.joaoasantana.core.common"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    implementation(platform(libs.compose))
    androidTestImplementation(platform(libs.compose))
    implementation(libs.compose.material)
    implementation(libs.compose.preview)
    androidTestImplementation(libs.compose.test)
    debugImplementation(libs.compose.test.manifest)

    implementation(libs.koin)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.lifecycle.compose)

    implementation(libs.navigation.compose)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)

    implementation(libs.room)
    implementation(libs.room.ktx)

    testImplementation(libs.junit)

    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)

    androidTestImplementation(libs.androidx.junit)
}