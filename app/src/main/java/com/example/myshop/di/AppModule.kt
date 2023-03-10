package com.example.myshop.di

import com.example.myshop.BuildConfig
import com.example.myshop.data.Api
import com.example.myshop.data.NetworkHandler
import com.example.myshop.data.usecases.GetProducts
import com.example.myshop.data.usecases.GetProductsImpl
import com.example.myshop.framework.NetworkHandlerImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductsApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkHandler(networkHandlerImpl: NetworkHandlerImpl): NetworkHandler = networkHandlerImpl

    @Provides
    @Singleton
    fun provideGetProducts(useCase: GetProductsImpl): GetProducts = useCase

    @Provides
    @Singleton
    internal fun providesDispatchersProvider(dispatchersProvider: DispatchersProviderImpl): DispatchersProvider = dispatchersProvider


}