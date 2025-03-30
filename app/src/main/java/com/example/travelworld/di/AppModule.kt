package com.example.travelworld.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.travelworld.data.SharedPrefsManager
import com.example.travelworld.data.TripRepositoryImpl
import com.example.travelworld.data.local.TripDatabase
import com.example.travelworld.data.local.dao.SubTripDao
import com.example.travelworld.data.local.dao.TripDao
import com.example.travelworld.domain.repository.TripRepository
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import firebase.com.protolitewrapper.BuildConfig
import javax.inject.Singleton

@dagger.Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @dagger.Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences("${BuildConfig.APPLICATION_ID}_preferences", Context.MODE_PRIVATE)

    // context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE) //bad implementation

    @dagger.Provides
    @Singleton
    fun provideSharedPrefsManager(
        sharedPreferences: SharedPreferences,
        @ApplicationContext context: Context
    ): SharedPrefsManager =
        SharedPrefsManager(sharedPreferences, context)



/*
    @dagger.Provides
    @Singleton
    fun provideFormValidationViewModel(
        @ApplicationContext context: Context
    ): FormValidationViewModel = FormValidationViewModel(context)
*/


    @dagger.Provides
    @Singleton
    fun provideTripDatabase(@ApplicationContext context: Context): TripDatabase {
        return Room.databaseBuilder(
            context,
            TripDatabase::class.java,
            "my_database_name"
        ).build()
    }

    @dagger.Provides
    fun provideTripDao(db: TripDatabase): TripDao = db.tripDao()

    @dagger.Provides
    fun provideSubTripDao(db: TripDatabase): SubTripDao = db.subTripDao()

    @dagger.Provides
    @Singleton
    fun provideTripRepository(tripDao: TripDao, subTripDao: SubTripDao): TripRepository = TripRepositoryImpl(tripDao, subTripDao)

}