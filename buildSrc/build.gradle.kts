object Dependencies {
    const val AndroidBuildTools = "com.android.tools.build:gradle:4.2.1"
}

plugins {
    `kotlin-dsl`
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation(Dependencies.AndroidBuildTools)
    implementation(kotlin("script-runtime"))
}