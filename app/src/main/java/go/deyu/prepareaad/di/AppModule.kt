package com.intersense.myneighbor.di

import android.content.Context
import android.location.Geocoder
import androidx.work.WorkManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.intersense.myneighbor.navi.NavigationManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import go.deyu.prepareaad.BuildConfig
import go.deyu.prepareaad.notification.NotificationUtil
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Module to tell Hilt how to provide instances of types that cannot be constructor-injected.
 *
 * As these types are scoped to the application lifecycle using @Singleton, they're installed
 * in Hilt's ApplicationComponent.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO


    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @Singleton
    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager = WorkManager.getInstance(context)

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()


    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val log = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().apply {
            connectTimeout(3, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                addInterceptor(log)
            }
            build()
        }.build()
    }

    @Singleton
    @Provides
    fun provideGeocoder(@ApplicationContext context: Context) = Geocoder(context)


}

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule{
    @Singleton
    @Provides
    fun provideNotificationUtils (@ApplicationContext context: Context): NotificationUtil = NotificationUtil(context)

}