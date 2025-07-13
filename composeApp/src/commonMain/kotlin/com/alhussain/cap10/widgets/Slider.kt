package com.alhussain.cap10.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import cap10.composeapp.generated.resources.Res
import cap10.composeapp.generated.resources.slider1
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class, ExperimentalResourceApi::class)
@Composable
fun SliderWidget(images: List<String>) {

    val pagerState = rememberPagerState(initialPage = 0, pageCount = {
        3
    })



    HorizontalPager(state = pagerState) { page ->
        // Our page content
        Image(
            painter = painterResource(Res.drawable.slider1),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }

}