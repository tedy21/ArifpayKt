buildscript {
    // ext.kotlin_version = ("1.8.22")
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        // classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}



android {
    namespace = "com.example.mylibrary"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

java {
    toolchain {
        //languageVersion = JavaLanguageVersion.of(17)
    }
}
//===============================

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("com.android.volley:volley:1.2.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.google.code.gson:gson:2.8.9")
}

// Publishing configuration
publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.tedy21"
            artifactId = "arifpaykt"
            version = "1.0.0"
            //packaging = "aar" // Specify the packaging type, e.g., "aar" for Android libraries
//            pom {
//                description = "First version release"
//            }
        }
    }
    repositories {
        maven {
            url = uri("https://github.com/tedy21/ArifpayKt") // Replace with your repository URL
        }
    }
}