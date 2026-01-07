plugins {
    alias(libs.plugins.kotlinJvm)
    kotlin("plugin.serialization") version "2.1.21"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
