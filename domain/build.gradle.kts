plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation("${CommonLibs.JAVAX_INJECT}:${CommonVersions.JAVAX_INJECT}")
    implementation("${CommonLibs.KOTLINX_COROUTINES}:${CommonVersions.KOTLINX_COROUTINES}")
    implementation("${CommonLibs.GSON}:${CommonVersions.GSON}")
}