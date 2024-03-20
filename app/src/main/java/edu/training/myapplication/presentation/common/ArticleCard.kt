package edu.training.myapplication.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import edu.training.myapplication.R
import edu.training.myapplication.domain.model.Article
import edu.training.myapplication.domain.model.Source
import edu.training.myapplication.presentation.Dimens
import edu.training.myapplication.ui.theme.NewsAppComposTheme


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (Article) -> Unit
) {
    val context = LocalContext.current
    Row(modifier = modifier.clickable { onClick(article) }) {

        AsyncImage(
            modifier = Modifier
                .size(Dimens.ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = "ArticleBlogImage",
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(Dimens.ArticleCardSize)
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    ))
                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))
                Icon(painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = "Time Icon",
                    modifier = Modifier.size(Dimens.SmallIconSize),
                    tint = colorResource(id = R.color.body))
                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    ))

            }

        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    NewsAppComposTheme {
        ArticleCard(article =  Article(
            author = "Emily Shapiro",
            title = "Several shot after Chiefs Super Bowl parade in Kansas City - ABC News",
            description = "Two armed people have been detained, Kansas City police said.",
            url = "https://abcnews.go.com/US/shooting-reported-kansas-city-after-chiefs-super-bowl/story?id=107238682",
            urlToImage = "https://i.abcnewsfe.com/a/7edb6c4c-9969-4047-9272-83c04ae8bd71/chiefs-rally-shooting-gty-jt-240214_1707941812285_hpMain_16x9.jpg?w=1600",
            publishedAt = "2024-02-14T20:15:00Z",
            content = "One person is dead and nine are injured from a shooting in Kansas City, Missouri, following the parade and rally for the Chiefs' Super Bowl win, according to the Kansas City Fire Department.\r\nThree v… [+1590 chars]",
            source = Source("abc-news","ABC News")
        )){}
    }
}
