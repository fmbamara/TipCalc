plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.example.tipcalc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tipcalc"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // AndroidX Core for compatibility
    implementation(libs.androidx.core.ktx)
    // JUnit for local testing
    testImplementation(libs.junit)
    // AndroidJUnit for instrumented testing
    androidTestImplementation(libs.androidx.junit)
    // AppCompat is necessary for this type of UI setup
    implementation("androidx.appcompat:appcompat:1.6.1")
}

