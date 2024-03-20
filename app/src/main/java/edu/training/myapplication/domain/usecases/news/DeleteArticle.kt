package edu.training.myapplication.domain.usecases.news

import edu.training.myapplication.data.local.NewsDao
import edu.training.myapplication.domain.model.Article

class DeleteArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.delete(article)
    }
}