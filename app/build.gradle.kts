plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.mihaidornea.maily"
    compileSdk = CommonVersions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.mihaidornea.maily"
        minSdk = CommonVersions.MIN_SDK
        targetSdk = CommonVersions.TARGET_SDK
        versionCode = CommonVersions.VERSION_CODE
        versionName = CommonVersions.VERSION_NAME

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

    composeOptions.kotlinCompilerExtensionVersion = CommonVersions.COMPOSE_COMPILER

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("${AppLibs.ANDROIDX_CORE}:${AppLibsVersions.ANDROIDX_CORE}")
    implementation("${AppLibs.ANDROIDX_COMPOSE_UI}:${CommonVersions.COMPOSE}")
    implementation("${AppLibs.ANDROIDX_COMPOSE_MATERIAL3}:${AppLibsVersions.COMPOSE_MATERIAL3}")
    implementation("${AppLibs.ANDROIDX_LIFECYCLE}:${AppLibsVersions.ANDROIDX_LIFECYCLE}")
    implementation("${AppLibs.ANDROIDX_ACTIVITY_COMPOSE}:${AppLibsVersions.ANDROIDX_ACTIVITY_COMPOSE}")
    implementation("${AppLibs.ANDROIDX_NAVIGATION_COMPOSE}:${AppLibsVersions.ANDROIDX_NAVIGATION_COMPOSE}")
    implementation("${CommonLibs.HILT_ANDROID}:${CommonVersions.HILT}")
    kapt("${CommonLibs.HILT_COMPILER}:${CommonVersions.HILT}")

    testImplementation("${CommonLibs.Test.JUNIT}:${CommonVersions.Test.JUNIT}")
    androidTestImplementation("${AppLibs.Test.ANDROIDX_TEST_JUNIT}:${AppLibsVersions.Test.ANDROIDX_TEST_JUNIT}")
    androidTestImplementation("${AppLibs.Test.ANDROIDX_TEST_ESPRESSO}:${AppLibsVersions.Test.ANDROIDX_TEST_ESPRESSO}")

    implementation("${AppLibs.Debug.ANDROIDX_COMPOSE_UI_TOOLING}:${CommonVersions.COMPOSE}")
    implementation("${AppLibs.Debug.ANDROIDX_COMPOSE_UI_TOOLING_PREVIEW}:${CommonVersions.COMPOSE}")
}