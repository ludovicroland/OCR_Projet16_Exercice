import com.android.build.gradle.BaseExtension
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("jacoco")
    id("org.sonarqube") version "5.1.0.4882"
}

/**

sonar {
    properties {
        property("sonar.projectKey", "JumpingKeyCaps_OCR_Projet16_Exercice")
        property("sonar.organization", "jumpingkeycaps")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.token","b12d279ff57ed81c228340e4a64fa05af00425f7")
        property("sonar.sources", "src/main")
        property("sonar.tests", "src/test")
        property("sonar.java.binaries", "${project.buildDir}/intermediates/classes/debug")

        property("sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml")

    }
}

*/
//Signature app
//val keystorePropertiesFile = rootProject.file("keystore.properties")
//val keystoreProperties = Properties()
//keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
//    signingConfigs {
//        create("release") {
//            storeFile = file(keystoreProperties["storeFile"] as String)
//            storePassword = keystoreProperties["storePassword"] as String
//            keyAlias = keystoreProperties["keyAlias"] as String
//            keyPassword = keystoreProperties["keyPassword"] as String
//        }
//    }
    namespace = "com.openclassrooms.arista"
    compileSdk = 35

    testCoverage {
        version = "0.8.8"
    }

    testOptions {
        animationsDisabled = true
        unitTests.isIncludeAndroidResources = true
    }


    defaultConfig {
        applicationId = "com.openclassrooms.arista"
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
//            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
        }

    }
    compileOptions {
        // Flag to enable support for the new language APIs
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }



    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}



dependencies {

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("androidx.activity:activity-ktx:1.10.0")
    implementation("androidx.fragment:fragment-ktx:1.8.5")

    //Tests
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
    testImplementation("org.mockito:mockito-core:5.3.1")

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")

    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.test:rules:1.6.1")
    implementation("androidx.tracing:tracing:1.2.0")
    implementation(kotlin("script-runtime"))


}
kapt {
    correctErrorTypes = true
}

tasks.withType<Test> {
    extensions.configure(JacocoTaskExtension::class) {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*")
    }
}

val androidExtension = extensions.getByType<BaseExtension>()

val jacocoTestReport by tasks.registering(JacocoReport::class) {
    dependsOn("testDebugUnitTest","connectedDebugAndroidTest", "createDebugCoverageReport")
    group = "Reporting"
    description = "Generate Jacoco coverage reports"

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    val fileFilter = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/di/**",
        "**/hilt/**",
        "**/Hilt_*.*",
        "**/com/openclassrooms/arista/ui/exercise/*$*.class",
        "**/com/openclassrooms/arista/ui/sleep/*$*.class",
        "**/com/openclassrooms/arista/ui/user/*$*.class",
        "**/com/openclassrooms/arista/data/*$*.class",
        "**/com/openclassrooms/arista/domain/model/*$*.class",
        "**/com/openclassrooms/arista/MainApplication.class",
        "**/com/openclassrooms/arista/ui/MainActivity.class",

        )

    val kotlinDebugClassesDir = fileTree("${project.buildDir}/tmp/kotlin-classes/debug/") {
        exclude(fileFilter)
    }

    val mainSrc = androidExtension.sourceSets.getByName("main").java.srcDirs

    classDirectories.setFrom(kotlinDebugClassesDir)
    sourceDirectories.setFrom(files(mainSrc))
    executionData.setFrom(
        fileTree(project.buildDir) {
            include(
                "**/*.exec", "**/*.ec"
            )
        }
    )
}




