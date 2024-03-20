package edu.training.myapplication.data.remote.dto

import edu.training.myapplication.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)