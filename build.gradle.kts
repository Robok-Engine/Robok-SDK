plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  `maven-publish`
}

group = "org.robok"
version = libs.versions.lib.version.get()

android {
  namespace = "org.robok"
  compileSdk = libs.versions.android.compileSdk.get().toInt()
  buildToolsVersion = libs.versions.android.buildToolsVersion.get()
  
  defaultConfig {
    minSdk = libs.versions.android.minSdk.get().toInt()

    ndk {
      abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
    }
  }
  
  buildTypes {
    release {
      isMinifyEnabled = false
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }

  kotlin {
    jvmToolchain(21)
  }
}

dependencies {
  implementation("androidx.annotation:annotation:1.9.1")
}

afterEvaluate {
  publishing {
    publications {
      register("mavenRelease", MavenPublication::class) {
        groupId = "org.robok"
        artifactId = "robok-sdk"
        version = libs.versions.lib.version.get()
        from(components["release"])
      }
    }
  }
}
