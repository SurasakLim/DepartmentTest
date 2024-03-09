plugins {
    id("department.ModulePlugin.convention")
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
    id("department.dependency-sharing.convention")
}

android {
    namespace = "com.example.core"
}

dependencies {
    implementation(Retrofit.gson)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}