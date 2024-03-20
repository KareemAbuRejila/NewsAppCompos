package edu.training.myapplication.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import edu.training.myapplication.R
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.presentation.Dimens
import edu.training.myapplication.presentation.common.ArticleList
import edu.training.myapplication.presentation.common.SearchBar
import edu.training.myapplication.presentation.navgraph.Route
import edu.training.myapplication.ui.theme.NewsAppComposTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount >10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString (separator = " \uD83d\uDFE5"){it.title}
            }else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.MediumPadding1)
            .statusBarsPadding()
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Blog Image",
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Dimens.MediumPadding1)
        )
        
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

        SearchBar(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = { navigateToSearch() },
            onSearch = {}
        )

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )
        
        Spacer(modifier = Modifier.height(Dimens.MediumPadding1))

        ArticleList(
            modifier = Modifier.padding(horizontal = Dimens.MediumPadding1),
            articles = articles,
            onClick = { navigateToDetails(it)}
        )
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    NewsAppComposTheme {
//        HomeScreen(articles = , navigate = {})
    }
}