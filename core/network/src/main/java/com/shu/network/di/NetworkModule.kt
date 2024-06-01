package com.shu.network.di

import com.shu.bascket.data.BasketRepository
import com.shu.catolog.data.Repository
import com.shu.database.FoodieDao
import com.shu.detail.data.DetailRepository
import com.shu.network.BasketRepositoryImpl
import com.shu.network.DetailRepositoryImpl
import com.shu.network.RepositoryImpl
import com.shu.network.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://anika1d.github.io/WorkTestServer/"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun providesRepository(
        api: ServiceApi,
        dao: FoodieDao
    ): Repository {
        return RepositoryImpl(api,dao)
    }

    @Provides
    fun providesBasketRepository(
        dao: FoodieDao
    ): BasketRepository {
        return BasketRepositoryImpl(dao)
    }
    @Provides
    fun providesDetailRepository(
        dao: FoodieDao
    ): DetailRepository {
        return DetailRepositoryImpl(dao)
    }

    @Provides
    fun provideRetrofit(): ServiceApi = Retrofit.Builder()
        .client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            }).build()
        )
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ServiceApi::class.java)


}