package com.example.departmentapp.gradle

import AndroidX
import DaggerHilt
import Kotlin
import Navigation
import Retrofit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies

class DependencySharingConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation(AndroidX.coreKtx)
                implementation(AndroidX.lifecycle)
                implementation(AndroidX.appCompat)
                implementation("androidx.activity:activity-ktx:1.8.0")
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
                implementation("com.google.android.material:material:1.8.0")
                implementation("androidx.constraintlayout:constraintlayout:2.1.4")
                implementation("androidx.recyclerview:recyclerview:1.3.1")
                implementation("androidx.cardview:cardview:1.0.0")
                implementation(Retrofit.okHttp)
                implementation(Retrofit.retrofit)
                implementation(Retrofit.okHttpLoggingInterceptor)
                implementation(Retrofit.moshiConverter)
                implementation(DaggerHilt.hiltAndroid)
                implementation(Kotlin.rxandroid)
                implementation(Kotlin.rxkotlin)
                implementation(Navigation.nav_frament)
                implementation(Navigation.nav_ui)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
            }
        }
    }

    private fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
        add("implementation", dependencyNotation)
}