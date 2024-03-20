package edu.training.myapplication.presentation.bookmark

import edu.training.myapplication.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
