plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //implementation project(":bice-id-sdk-debug")
    //implementation("bice-id-sdk-debug")
    //implementation(group = "libs", name = "bice-id-sdk-debug", ext = "aar")
    implementation(files("libs/bice-id-sdk-debug.aar"))
    // compileOnly(files("libs/bice-id-sdk-debug.aar"))

    //api(libs.aws.auth.cognito) { isTransitive = true }
    //api(libs.aws.predictions) { isTransitive = true }
    //api(libs.aws.ui.liveness) { isTransitive = true }

    implementation(libs.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Vision Library
    implementation(libs.text.recognition)

    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    //implementation("androidx.camera:camera-extensions:1.3.4")
    implementation(libs.androidx.navigation.compose)
    implementation(libs.lottie)
    implementation(libs.lottie.compose)
    implementation(libs.jmrtd)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)
    implementation (libs.opencv)
    implementation(libs.coil.compose)
    implementation(libs.root.beer)
    implementation(libs.device.names)

    // Face Liveness
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.aws.auth.cognito)
    implementation(libs.aws.predictions)
    implementation(libs.aws.ui.liveness)
    implementation(libs.material3)

    implementation("com.github.mhshams:jnbis:1.1.0")
    implementation("commons-io:commons-io:2.11.0")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.65") // do not update
    implementation("net.sf.scuba:scuba-sc-android:0.0.18")
    implementation("com.github.ByteAmaze:RNCryptor-Android:1.0")
    implementation("com.datatheorem.android.trustkit:trustkit:1.1.3")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.android.play:integrity:1.1.0")
}