package edu.training.myapplication.presentation.deatils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.domain.usecases.news.NewsUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    var sideEffect by mutableStateOf<SideEffects?>(null)
        private set

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article == null)
                        upsertArticle(event.article)
                    else
                        deleteArticle(event.article)
                }
            }
            is DetailsEvent.RemoveSideEffect -> sideEffect = null
            else -> {}
        }
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCases.upsertArticle(article = article)
        sideEffect = SideEffects.Saved
    }
    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article = article)
        sideEffect = SideEffects.Removed
    }

}