plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.cookhelper.dynamic.theme"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.palette:palette:1.0.0")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.t8rin"
            artifactId = "dynamictheme"
            version = "1.0.3"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}