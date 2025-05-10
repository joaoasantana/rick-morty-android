plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false

    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    detekt {
        autoCorrect = true
        buildUponDefaultConfig = true
        config.setFrom(files("${project.rootDir}/.config/detekt/detekt.yml"))
        parallel = true
    }
}
