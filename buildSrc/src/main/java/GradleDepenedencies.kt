/**
 * Kotlin gradle dependecy management
 * check https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/
 */
object Versions {
    val gradlePlugin = "3.1.3"

    val androidSupportLib = "27.1.1"
    val constraintLayout = "1.1.2"
    val archExtensions = "1.1.1"

    val koin = "1.0.0-beta-6"
    val kotlin = "1.2.61"

    val androidTestSupportLib = "1.0.2"
    val espresso = "3.0.2"
    val junit = "4.12"
    val mockito = "2.18.3"
    val mockitoKotlin = "1.5.0"
}

object Libs {
    // android
    val archExtensions = "android.arch.lifecycle:extensions:${Versions.archExtensions}"

    // android support
    val appCompat = "com.android.support:appcompat-v7:${Versions.androidSupportLib}"
    val constraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"

    // gradle
    val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"

    // kotlin
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinAllOpen = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"

    // koin
    val koinAndroidViewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    val koinTest = "org.koin:koin-test:${Versions.koin}"

    // test
    val atsRunner = "com.android.support.test:runner:${Versions.androidTestSupportLib}"
    val atsRules = "com.android.support.test:rules:${Versions.androidTestSupportLib}"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    val junit = "junit:junit:${Versions.junit}"

    // mockito
    val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
    val mockitoKotin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"

}