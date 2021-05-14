/**
 * Created by JunhwaLee on 2021-05-14
 */

object Versions {
    const val kotlinVersions = "1.5.0"

    const val rxJava3Versions = "3.0.13-RC4"

    const val retrofitVersions = "2.9.0"
}

object Deps {
    // kotlin lib
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersions}"
    const val kotlinSTDLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersions}"

    // rx lib
    const val rxJava3 = "io.reactivex.rxjava3:rxjava:${Versions.rxJava3Versions}"

    // retrofit lib
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersions}"
    const val retrofit2RxJavaAdapter =
        "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitVersions}"
    const val retrofit2GsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersions}"

    // unit test lib
    const val testKotlinJDK8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersions}"
    const val testKotlin = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlinVersions}"
    const val testKotlinJunit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlinVersions}"
}

object BuildConfig {
    const val BASE_API_URL = "http://d2bab9i9pr8lds.cloudfront.net"
}

object BuildVersions {

}