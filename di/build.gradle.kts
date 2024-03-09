plugins {
    id("kotlin-kapt")
    id("department.ModulePlugin.convention")
    id("department.dependency-sharing.convention")
}

android {
    namespace = "com.example.di"
}

dependencies {
    kapt(DaggerHilt.hiltCompiler)
    implementation(project(":core"))
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}