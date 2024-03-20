package edu.training.myapplication.domain.usecases.news

import edu.training.myapplication.data.local.NewsDao
import edu.training.myapplication.domain.model.Article

class SelectArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article?{
       return newsDao.getArticle(url)
    }
}