package com.shu.database.di

import android.content.Context
import androidx.room.Room
import com.shu.database.FoodieDao
import com.shu.database.FoodiesDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): FoodiesDataBase =
        Room.databaseBuilder(
            checkNotNull(context.applicationContext),
            FoodiesDataBase::class.java,
            "foodies"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideWeatherDao(appDatabase: FoodiesDataBase): FoodieDao {
        return appDatabase.foodieDao()
    }

}
