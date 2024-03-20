package edu.training.myapplication.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.training.myapplication.domain.usecases.news.NewsUseCases
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state : State<SearchState> = _state

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }
            is SearchEvent.SearchNews -> {
                searchNews()

            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCases.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("abc-news","abc-news","al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }
}