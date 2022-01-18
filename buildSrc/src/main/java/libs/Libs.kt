package libs

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.0-alpha02"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.0.9"

    const val junit = "junit:junit:4.13"

    const val material = "com.google.android.material:material:1.3.0"


    object FB{
        const val version = "11.1.0"
        const val core = "com.facebook.android:facebook-core:$version"
        const val login = "com.facebook.android:facebook-login:$version"
    }
    object Kotlin {

        private const val version = "1.5.31"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
//         常見livedata
        private const val version = "1.4.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Accompanist {
//        https://github.com/google/accompanist 有一些Libs 幫助 Compose的
        private const val version = "0.16.1"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val pager = "com.google.accompanist:accompanist-pager:$version"
        const val permissions = "com.google.accompanist:accompanist-permissions:$version"
        const val systemUiController =  "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-rc01"
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val annotation = "androidx.annotation:annotation:1.3.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha06"
        }

        object Work{
            const val version = "2.5.0"
            const val work = "androidx.work:work-runtime-ktx:$version"
            const val mutiProcess =  "androidx.work:work-multiprocess:$version"

        }

        object Room{
            const val version = "2.3.0"
            const val kaptCompiler =  "androidx.room:room-compiler:$version"
            const val ktx =  "androidx.room:room-ktx:$version"
            const val common =  "androidx.room:room-common:$version"
            const val runtime =  "androidx.room:room-runtime:$version"

        }

        object Compose {
            const val snapshot = ""
            const val version = "1.1.0-alpha06"
            const val animation ="androidx.compose.animation:animation:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"

            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val test = "androidx.compose.ui:ui-test:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:${version}"
            const val viewBinding = "androidx.compose.ui:ui-viewbinding:$version"
            const val constraintlayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha08"
        }

        object Navigation {
            private const val version = "2.3.4"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }


        object Test{
            // Testing dependencies
            private const val junitextversion = "1.1.2"
            private const val version = "1.4.0-beta01"
            const val  espressoVersion = "3.4.0"
            const val  coreTestingVersion = "2.1.0"
            const val robolectricVersion = "4.7.3"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"
            const val robolectric =  "org.robolectric:robolectric:$robolectricVersion"

            const val uiTest = "androidx.compose.ui:ui-test-junit4:${Compose.version}"
            const val work =  "androidx.work:work-testing:${Work.version}"
            const val archCore = "androidx.arch.core:core-testing:$coreTestingVersion"
//            Android Testing
            const val espressoCore = "androidx.test.espresso:espresso-core:$espressoVersion"
            const val espressContrib = "androidx.test.espresso:espresso-contrib:$espressoVersion"
            const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Compose.version}"

            const val composeUi = "androidx.compose.ui:ui-test:${Compose.version}"
            const val composeUiJunit = "androidx.compose.ui:ui-test-junit4:${Compose.version}"



        }
        object Lifecycle {
            private const val version = "2.4.0"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha04"
        }

        object DataStore{
            private const val version = "1.0.0"
            const val sharedPreferences = "androidx.datastore:datastore-preferences:$version"
        }
    }
    object Moshi {
        private const val version = "1.12.0"
        const val moshi = "com.squareup.moshi:moshi:$version"
        const val moshiAdapter = "com.squareup.moshi:moshi-adapters:$version"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:$version"
        const val moshiKapt = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Retrofit{
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:$version"
        const val retrofitCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        const val retrofitLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.10.0-RC1"
    }
    object Coil{
        private const val version = "1.2.2"
        private const val accompanistVersion = "0.13.0"
        const val coil  = "io.coil-kt:coil:$version"
        const val coilCompose = "com.google.accompanist:accompanist-coil:$accompanistVersion"
    }

    object Hilt{
        private const val version = "2.37"
        private const val versionCompiler = "1.0.0"
        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val hiltDaggerCompilerKapt = "com.google.dagger:hilt-compiler:$version"
        const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
        const val hiltCompilerKapt = "androidx.hilt:hilt-compiler:$versionCompiler"
        const val hiltNavigationCompose= "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"

        object Test{
            const val hilt = "com.google.dagger:hilt-android-testing:$version"
            const val hiltDaggerCompilerKapt = "com.google.dagger:hilt-android-compiler:$version"
            const val hiltCompilerKapt = "androidx.hilt:hilt-compiler:$versionCompiler"
        }

    }

    // Hilt testing

}


//// AndroidJUnitRunner and JUnit Rules
//androidTestImplementation 'androidx.test:runner:1.4.0'
//androidTestImplementation 'androidx.test:rules:1.4.0'
//
//// Assertions
//androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//androidTestImplementation 'androidx.test.ext:truth:1.4.0'
//androidTestImplementation 'com.google.truth:truth:1.0.1'
//
//// Espresso dependencies
//androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//androidTestImplementation 'androidx.test.espresso:espresso-contrib:3.4.0'
//androidTestImplementation 'androidx.test.espresso:espresso-intents:3.4.0'
//androidTestImplementation 'androidx.test.espresso:espresso-accessibility:3.4.0'
//androidTestImplementation 'androidx.test.espresso:espresso-web:3.4.0'
//androidTestImplementation 'androidx.test.espresso.idling:idling-concurrent:3.4.0'
//
//// The following Espresso dependency can be either "implementation"
//// or "androidTestImplementation", depending on whether you want the
//// dependency to appear on your APK's compile classpath or the test APK
//// classpath.
//androidTestImplementation 'androidx.test.espresso:espresso-idling-resource:3.4.0