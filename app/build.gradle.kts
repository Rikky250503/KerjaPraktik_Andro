plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.projectkp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.projectkp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
//    plugins {
//        id ("com.android.tools.build") version "8.2.0" apply false
//    }

}

dependencies {

//    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation ("com.google.android.material:material:1.5.0")

    //implementation ("com.diogobernardino:williamchart:3.10.1")


    implementation (libs.mpandroidchart)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.activity)
    implementation(libs.appcompat)
    testImplementation(libs.junit)
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)

}