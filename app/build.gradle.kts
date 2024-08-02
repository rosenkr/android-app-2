import java.util.Properties
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinx.serialization)
    id("com.google.dagger.hilt.android") version "2.49"
    id("com.google.devtools.ksp")
    id("de.jensklingenberg.ktorfit") version "2.0.0"
}

android {
    namespace = "se.umu.alro0113.trackandbet"
    compileSdk = 34

    defaultConfig {
        applicationId = "se.umu.alro0113.trackandbet"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")


    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            android.buildFeatures.buildConfig = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
            excludes += "/META-INF/INDEX.LIST"
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
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.material.icons.extended.android)

    implementation(libs.vico.compose.m3) // charts and graphs
    implementation(libs.androidx.ui.text.google.fonts) // text fonts
    implementation(libs.androidx.appcompat)

    // Ktor for client-side
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)

    // General json handling and compose safe args navigation
    implementation(libs.kotlinx.serialization.json)

    // Dagger/Hilt using ksp instead of kapt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    // Arrow for "Either" blocks instead of regular try-catches, less verbose
    implementation(libs.arrow.core)
    implementation(libs.arrow.fx.coroutines)

    // Ktorfit for retrofit-like dependency injection with ktor
    implementation(libs.ktorfit.lib)

    // 2.8.0 beta version, navigation with safe args, similar to safe args in xml
    implementation(libs.androidx.navigation.compose)

    // Reflection to extract member variable name for labels in a composable
    implementation(libs.kotlin.reflect)

    // Api instead of implementation is only relevant for KMP projects with different source sets such as android,desktop,ios
    api(libs.datastore.preferences)
    api(libs.datastore)

    // Splash Screen
    implementation(libs.androidx.core.splashscreen)

}
