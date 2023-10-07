package com.isaacdelosreyes.valorantforlogixs.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.isaacdelosreyes.valorantforlogixs.ui.theme.BlackGray
import com.isaacdelosreyes.valorantforlogixs.ui.theme.Cyprus
import com.isaacdelosreyes.valorantforlogixs.ui.theme.Tugnsten

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetailScreen: (String) -> Unit
) {

    val state = viewModel.state

    Column(modifier = Modifier.background(BlackGray)) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 10.dp, start = 10.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(weight = 1f)
        ) {

            items(state.agents) {

                val colors = it.backgroundGradientColors.map { color ->
                    val colorAndroid = "#$color".toColorInt()
                    Color(colorAndroid)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clickable {
                            navigateToDetailScreen(it.uuid)
                        }
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 60.dp)
                            .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
                            .background(Brush.verticalGradient(colors = colors))
                    )

                    AsyncImage(
                        model = it.fullPortraitV2,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                    Column(
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                )
                            )
                            .background(Cyprus)
                            .align(BottomCenter)
                    ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = it.displayName,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = Tugnsten,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = it.developerName,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}