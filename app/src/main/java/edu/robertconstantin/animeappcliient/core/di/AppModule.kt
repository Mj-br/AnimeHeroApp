package edu.robertconstantin.animeappcliient.core.di

import android.app.Application
import coil.ComponentRegistry
import coil.ImageLoader
import coil.decode.SvgDecoder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

//Provee el cliente HTTP

//Este lo usa el  retrofit, es el cliente HTTP

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton //Singleton para que solo haya una instancia de este objeto en toda la app
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
    }

    //ImageLoader
    @Provides
    @Singleton //Singleton para que solo haya una instancia de este objeto en toda la app
    fun provideImageLoader(app: Application): ImageLoader {
        return ImageLoader.Builder(app)
            .crossfade(true)
            .components(fun ComponentRegistry.Builder.() {
                add(SvgDecoder.Factory())
            }).build()
    }
}