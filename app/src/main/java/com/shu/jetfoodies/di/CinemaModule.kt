package com.shu.jetfoodies.di

import com.shu.network.Repository
import com.shu.network.RepositoryImpl
import com.shu.network.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CinemaModule {

    @Provides
    fun providesRepository(
        api: ServiceApi
    ): Repository {
        return RepositoryImpl(api)
    }

}