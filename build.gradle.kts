// Top-level build.gradle.kts file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.7.3" apply false // Use the correct version of the Android plugin
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
}

//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
//}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}
