package edu.training.myapplication.presentation.onboarding

import androidx.annotation.DrawableRes
import edu.training.myapplication.R

data class Page(
    val title:String,
    val desc:String,
    @DrawableRes val img:Int
)
val pages = listOf(
    Page(
        title = "First Page Title",
        desc = "this is the Description of page 1,this is the Description of page 1this is the Description of page 1this is the Description of page 1",
        img = R.drawable.onboarding1
    ),
    Page(
        title = "Second Page Title",
        desc = "this is the Description of page 2,this is the Description of page 2,this is the Description of page 2,this is the Description of page 2,",
        img = R.drawable.onboarding2
    ),
    Page(
        title = "third Page Title",
        desc = "this is the Description of page 3,this is the Description of page 3this is the Description of page 3this is the Description of page 3this is the Description of page 3",
        img = R.drawable.onboarding3
    )
)
