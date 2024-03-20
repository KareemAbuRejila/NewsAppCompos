package edu.training.myapplication.presentation.deatils

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edu.training.myapplication.R
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.domain.model.Source
import edu.training.myapplication.presentation.Dimens.ArticleImageHeight
import edu.training.myapplication.presentation.Dimens.MediumPadding1
import edu.training.myapplication.presentation.deatils.components.DetailsTopBar
import edu.training.myapplication.ui.theme.NewsAppComposTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null)
                        context.startActivity(it)
                }
            },
            onBookMarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick = navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1,
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data(article.urlToImage)
                        .diskCacheKey("article-img-${article.author},${article.publishedAt}")
                        .build(),
                    contentDescription = "DetailsScreen-Art-Img",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop,
                    onLoading = {}
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {
    NewsAppComposTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)){
            DetailsScreen(
                article = Article(
                    author = "Emily Shapiro",
                    title = "Several shot after Chiefs Super Bowl parade in Kansas City - ABC News",
                    description = "Two armed people have been detained, Kansas City police said.",
                    url = "https://abcnews.go.com/US/shooting-reported-kansas-city-after-chiefs-super-bowl/story?id=107238682",
                    urlToImage = "https://i.abcnewsfe.com/a/63222c9f-7895-4ef1-853f-25426fe2950c/chiefs-rally-shooting-01-gty-jt-240214_1707941943354_hpEmbed_3x2.jpg",
                    publishedAt = "2024-02-14T20:15:00Z",
                    content = "One person is dead and nine are injured from a shooting in Kansas City, Missouri, following the parade and rally for the Chiefs' Super Bowl win, according to the Kansas City Fire Department.\r\nThree v… [+1590 chars]",
                    source = Source("abc-news", "ABC News")
                ), event = {},
                navigateUp = {}
            )
        }
    }
}