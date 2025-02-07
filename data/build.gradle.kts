plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mihaidornea.maily.data"
    testNamespace = "com.mihaidornea.maily.data.test"
    compileSdk = CommonVersions.COMPILE_SDK
    defaultConfig {
        minSdk = CommonVersions.MIN_SDK

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://randomuser.me/api/\""
        )
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

    kotlinOptions.jvmTarget = "17"
    buildFeatures.buildConfig = true
}

dependencies {
    implementation(project(":domain"))
    implementation("${CommonLibs.JAVAX_INJECT}:${CommonVersions.JAVAX_INJECT}")
    implementation("${CommonLibs.KOTLINX_COROUTINES}:${CommonVersions.KOTLINX_COROUTINES}")
    implementation("${CommonLibs.HILT_ANDROID}:${CommonVersions.HILT}")
    kapt("${CommonLibs.HILT_COMPILER}:${CommonVersions.HILT}")

    implementation("${CommonLibs.GSON}:${CommonVersions.GSON}")
    implementation("${DataLibs.RETROFIT}:${DataLibsVersions.RETROFIT}")
    implementation("${DataLibs.RETROFIT_GSON_CONVERTER}:${DataLibsVersions.RETROFIT}")
    implementation("${DataLibs.LOGGING_INTERCEPTOR}:${DataLibsVersions.LOGGING_INTERCEPTOR}")
}