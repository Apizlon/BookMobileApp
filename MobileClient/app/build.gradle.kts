plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.apiclient.bookstoreapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.apiclient.bookstoreapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = false
    }
}

dependencies {
    // базовые зависимости AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // классические Material Components без Compose
    implementation("com.google.android.material:material:1.10.0")

    // viewModels
    implementation("androidx.activity:activity-ktx:1.7.0")

    // constraintLayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Retrofit для работы с REST
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Kotlin Coroutines для асинхронных операций
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Jetpack ViewModel и LiveData для MVVM
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // тесты
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}