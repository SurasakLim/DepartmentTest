import Testing.fragment_version

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("department.dependency-sharing.convention")
}

android {
    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.constraintlayout:constraintlayout-core:1.0.4")
    implementation("com.google.ar:core:1.41.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    kapt(DaggerHilt.hiltCompiler)
    implementation("com.github.bumptech.glide:glide:4.13.2")
    implementation("com.jakewharton.rxbinding3:rxbinding:3.1.0")
    kapt("com.github.bumptech.glide:compiler:4.13.2")

    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation(Testing.mockk)
    testImplementation(Testing.mockkAndroid)
    testImplementation(Testing.mockWebServer)
    testImplementation(Testing.coroutines)
    implementation(project(":core"))
    implementation(project(":di"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation(Testing.hiltTesting)

    debugImplementation("androidx.fragment:fragment-testing:$fragment_version")
}