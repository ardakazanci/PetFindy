package scripts

plugins { id("com.android.application")  }

private object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

android {
    buildTypes {
        maybeCreate(BuildTypes.DEBUG).apply {
            isMinifyEnabled = false
        }

        maybeCreate(BuildTypes.RELEASE).apply {
            isMinifyEnabled = false
        }
    }
}