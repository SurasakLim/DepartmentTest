import org.gradle.kotlin.dsl.`kotlin-dsl`

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven {
        url = uri("https://plugins.gradle.org/m2/")
    }
}

dependencies{
    implementation("com.android.tools.build:gradle:8.1.2")
    implementation("org.jetbrains.kotlin.android:org.jetbrains.kotlin.android.gradle.plugin:1.9.10")
    implementation("com.google.gms:google-services:4.4.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.48")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
}

gradlePlugin {
    plugins {
        register("dependencySharingConventionPlugin") {
            id = "department.dependency-sharing.convention"
            implementationClass = "com.example.departmentapp.gradle.DependencySharingConventionPlugin"
        }
    }
    plugins {
        register("ModulePlugin") {
            id = "department.ModulePlugin.convention"
            implementationClass = "com.example.departmentapp.gradle.ModulePlugin"
        }
    }
    plugins {
        register("AppModulePlugin") {
            id = "department.AppModulePlugin.convention"
            implementationClass = "com.example.departmentapp.gradle.ApplicationModulePlugin"
        }
    }
}

plugins {
    `kotlin-dsl`
}
