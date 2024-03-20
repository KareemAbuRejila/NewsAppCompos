package edu.training.myapplication.domain.usecases.news

import edu.training.myapplication.data.local.NewsDao
import edu.training.myapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke() = newsDao.getArticles()
}