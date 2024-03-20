package edu.training.myapplication.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.presentation.Dimens

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
        contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
    ) {
        items(count = articles.size) {index->
            articles[index].let{ art->
                ArticleCard(article = art, onClick = { onClick(art) })
            }

        }
    }
}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1),
            contentPadding = PaddingValues(all = Dimens.ExtraSmallPadding2)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { art ->
                    ArticleCard(article = art, onClick = { onClick(art) })
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>,
): Boolean {

    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyErrScreen()
            false
        }

        else -> true
    }
}

@Preview
@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding1)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.MediumPadding1)
            )
        }
    }
}

