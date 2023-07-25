package com.tourism.breeddogs.common.di

import com.tourism.breeddogs.common.instance.Instance.Companion.BASE_URL
import com.tourism.breeddogs.data.BreedApi
import com.tourism.breeddogs.data.BreedRepositoryImpl
import com.tourism.breeddogs.domain.BreedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BreedModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesBreedsAPI(): BreedApi{
        return providesRetrofit().create(BreedApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(api: BreedApi): BreedRepository {
        return BreedRepositoryImpl(api)
    }
}