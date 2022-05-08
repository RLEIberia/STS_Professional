package com.rle.STS.di

import android.content.Context
import androidx.room.Room
import com.rle.STS.data.BBDD.STSDao
import com.rle.STS.data.BBDD.STSDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //Database
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): STSDatabase =
        Room.databaseBuilder(
            context,
            STSDatabase::class.java,
            "sts_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    //Instanciar Dao
    @Singleton
    @Provides
    fun provideSTSDao(stsDatabase: STSDatabase): STSDao =
        stsDatabase.STSDao()

    //TODO API PROVIDER

    //TODO DAO PROVIDER
}