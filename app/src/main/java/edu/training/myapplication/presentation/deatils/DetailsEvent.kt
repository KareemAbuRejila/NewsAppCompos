package edu.training.myapplication.presentation.deatils

import edu.training.myapplication.domain.model.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()

}
sealed class SideEffects {
    object Saved: SideEffects()
    object Removed: SideEffects()
}