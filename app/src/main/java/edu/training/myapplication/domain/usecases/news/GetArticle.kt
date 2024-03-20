package edu.training.myapplication.domain.usecases.news

import androidx.paging.PagingData
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article?{
        return newsRepository.getArticle(url = url)
    }
}