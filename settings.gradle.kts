pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
//    plugins {
//        id("com.android.application") version "8.7.3" // Ensure this version matches the other project configurations
//        id("org.jetbrains.kotlin.android") version "1.8.0"
//    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ModuleFind"
include(":app")
