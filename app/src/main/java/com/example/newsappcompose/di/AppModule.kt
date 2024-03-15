package com.example.newsappcompose.di

import android.app.Application
import com.example.newsappcompose.data.manager.LocalUserManagerImpl
import com.example.newsappcompose.domain.manager.LocalUserManager
import com.example.newsappcompose.domain.usecase.AppEntryUseCases
import com.example.newsappcompose.domain.usecase.ReadAppEntry
import com.example.newsappcompose.domain.usecase.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesLocalUserMenager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}