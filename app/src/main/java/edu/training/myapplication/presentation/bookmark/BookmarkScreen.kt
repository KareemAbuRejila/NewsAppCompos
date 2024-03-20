package edu.training.myapplication.presentation.bookmark

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import edu.training.myapplication.R
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.presentation.Dimens.MediumPadding1
import edu.training.myapplication.presentation.common.ArticleList
import edu.training.myapplication.presentation.navgraph.Route
import edu.training.myapplication.ui.theme.NewsAppComposTheme

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetailsScreen: (Article) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = MediumPadding1, start = MediumPadding1, end = MediumPadding1)
    ) {
        Text(text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title))

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticleList(articles = state.articles, onClick = {
            navigateToDetailsScreen(it)
        })
    }


}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun BookmarkScreenPreview(){
    NewsAppComposTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
            BookmarkScreen(state = BookmarkState(), navigateToDetailsScreen = {})
        }
    }
}