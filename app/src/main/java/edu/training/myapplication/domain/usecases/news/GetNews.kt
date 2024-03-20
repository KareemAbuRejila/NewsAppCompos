package edu.training.myapplication.domain.usecases.news

import androidx.paging.PagingData
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.getNews(sources = sources)
    }
}