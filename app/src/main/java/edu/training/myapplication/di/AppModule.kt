package edu.training.myapplication.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.training.myapplication.data.local.NewsDao
import edu.training.myapplication.data.local.NewsDataBase
import edu.training.myapplication.data.local.NewsTypeConverter
import edu.training.myapplication.data.manger.LocalUserMangerImpl
import edu.training.myapplication.data.remote.NewsApi
import edu.training.myapplication.data.repository.NewsRepositoryImpl
import edu.training.myapplication.domain.manger.LocalUserManger
import edu.training.myapplication.domain.repository.NewsRepository
import edu.training.myapplication.domain.usecases.app_entry.AppEntryUseCases
import edu.training.myapplication.domain.usecases.app_entry.ReadAppEntry
import edu.training.myapplication.domain.usecases.app_entry.SaveAppEntry
import edu.training.myapplication.domain.usecases.news.DeleteArticle
import edu.training.myapplication.domain.usecases.news.GetNews
import edu.training.myapplication.domain.usecases.news.NewsUseCases
import edu.training.myapplication.domain.usecases.news.SearchNews
import edu.training.myapplication.domain.usecases.news.SelectArticle
import edu.training.myapplication.domain.usecases.news.SelectArticles
import edu.training.myapplication.domain.usecases.news.UpsertArticle
import edu.training.myapplication.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ): AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)


    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases = NewsUseCases(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository),
        upsertArticle = UpsertArticle(newsDao),
        deleteArticle = DeleteArticle(newsDao),
        selectArticles = SelectArticles(newsDao),
        selectArticle = SelectArticle(newsDao)
    )


    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDataBase = Room
        .databaseBuilder(
            context = application,
            klass = NewsDataBase::class.java,
            name = Constants.NEWS_DB
        ).addTypeConverter(NewsTypeConverter())
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideNewsDAO(
        newsDataBase: NewsDataBase
    ): NewsDao = newsDataBase.newsDao

}