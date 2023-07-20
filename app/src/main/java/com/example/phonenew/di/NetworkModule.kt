package com.example.phonenew.di

import com.example.phonenew.data.network.ProductApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Se define un alcance para la instancia (Retrofit lo necesitamos en toda la app)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    // Como ya esta creado retrofit como provide, podemos pedirlo en cualquier parte
    fun provideQuoteApiClient(retrofit : Retrofit) : ProductApiClient {
        return retrofit.create(ProductApiClient::class.java)
    }

}