package edu.training.myapplication.presentation.common

import android.widget.Toast
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.training.myapplication.ui.theme.NewsAppComposTheme
import edu.training.myapplication.ui.theme.WhiteGray
import kotlin.coroutines.coroutineContext

@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }

}

@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(text = text,
            color = WhiteGray,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold))
    }

}

@Preview()
@Composable
fun NewsButtonPreview() {
    NewsAppComposTheme {
        NewsButton(text = "Get Started") {

        }
    }
}
@Preview()
@Composable
fun NewsTextButtonPreview() {
    NewsAppComposTheme {
        NewsTextButton(text = "Back") {

        }
    }
}
