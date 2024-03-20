package edu.training.myapplication.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.presentation.Dimens.MediumPadding1
import edu.training.myapplication.presentation.common.ArticleList
import edu.training.myapplication.presentation.common.SearchBar
import edu.training.myapplication.presentation.navgraph.Route
import edu.training.myapplication.ui.theme.NewsAppComposTheme

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetailsScreen: (Article) -> Unit
){

    Column(
        modifier = Modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly =false,
            onValueChange ={event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = {event(SearchEvent.SearchNews)}
        )

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                articles = articles,
                onClick = {article ->
                    navigateToDetailsScreen(article)
                }
            )
        }
    }

}

@Preview
@Composable
fun SearchScreenPreview(){
    NewsAppComposTheme {
        SearchScreen(SearchState(),{},{})
    }
}