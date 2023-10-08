package com.isaacdelosreyes.valorantforlogixs.maps.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.isaacdelosreyes.valorantforlogixs.ui.theme.BlackGray
import com.isaacdelosreyes.valorantforlogixs.ui.theme.Tugnsten

@Composable
fun MapsScreen(viewModel: MapsViewModel = hiltViewModel(), navigateToMapDetail: (String) -> Unit) {

    val state = viewModel.state

    LazyColumn(
        content = {
            items(state.maps) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { navigateToMapDetail(it.uuid) }
                ) {

                    AsyncImage(
                        model = it.splash,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Black
                                    )
                                )
                            )
                    )

                    Text(
                        text = it.displayName,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(start = 20.dp, bottom = 20.dp),
                        fontFamily = Tugnsten,
                        color = Color.White,
                        fontSize = 28.sp
                    )
                }
            }
        },
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(
            top = 20.dp,
            bottom = 10.dp,
            start = 10.dp,
            end = 10.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .background(BlackGray)
    )
}