@Suppress("DSL_SCOPE_VIOLATION") // This remove gradle errors by the IDE when use version catalogs
plugins {
    `java-library`
    alias(libs.plugins.kotlin.library)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
    withJavadocJar()
}

dependencies {

    implementation(libs.bundles.common.implementation)
    testImplementation(libs.bundles.common.test)

}