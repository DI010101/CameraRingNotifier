plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {

    namespace = "com.cameraring.notifier"

    compileSdk = 35

    defaultConfig {

        applicationId = "com.cameraring.notifier"

        minSdk = 29
        targetSdk = 35

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    val composeBom =
        platform("androidx.compose:compose-bom:2025.01.01")

    implementation(composeBom)

    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0")

    implementation("androidx.activity:activity-compose:1.10.1")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("androidx.compose.material3:material3")

    implementation(
        "androidx.navigation:navigation-compose:2.8.8"
    )

    implementation(
        "androidx.datastore:datastore-preferences:1.1.1"
    )

    implementation(
        "androidx.lifecycle:lifecycle-service:2.9.0"
    )

    implementation(
        "androidx.lifecycle:lifecycle-runtime-compose:2.9.0"
    )

    implementation(
        "androidx.core:core-splashscreen:1.0.1"
    )

    debugImplementation(
        "androidx.compose.ui:ui-tooling"
    )

    debugImplementation(
        "androidx.compose.ui:ui-test-manifest"
    )
}