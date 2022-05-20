package com.rle.STS.di

import android.content.Context
import androidx.room.Room
import com.rle.STS.data.BBDD.STSDao
import com.rle.STS.data.BBDD.STSDatabase
import com.rle.STS.network.StsAPI
import com.rle.STS.repository.DataStoreRepository
import com.rle.STS.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

    //Instanciar API Provider
    @Singleton
    @Provides
    fun provideStsAPI(): StsAPI {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StsAPI::class.java)
    }

    //Instanciar DataStore Provider
    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context) = DataStoreRepository(context)

}