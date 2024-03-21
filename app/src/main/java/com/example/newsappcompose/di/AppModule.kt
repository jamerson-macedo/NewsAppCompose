package com.example.newsappcompose.di

import android.app.Application
import com.example.newsappcompose.data.manager.LocalUserManagerImpl
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domain.manager.LocalUserManager
import com.example.newsappcompose.domain.repository.NewsRepository
import com.example.newsappcompose.domain.usecase.app_entry.AppEntryUseCases
import com.example.newsappcompose.domain.usecase.app_entry.ReadAppEntry
import com.example.newsappcompose.domain.usecase.app_entry.SaveAppEntry
import com.example.newsappcompose.domain.usecase.news.GetNews
import com.example.newsappcompose.domain.usecase.news.NewsUseCases
import com.example.newsappcompose.domain.usecase.news.SearchNews
import com.example.newsappcompose.util.Constants.BASE_URL
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
    fun providesLocalUserMenager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    // os casos de uso precisando do local manager
    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(NewsApi::class.java)
    }


    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi): NewsRepository = NewsRepositoryImpl(newsApi)


    @Provides
    @Singleton
    fun providesGetNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }
}