package com.isaacdelosreyes.valorantforlogixs.map.maps.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.isaacdelosreyes.valorantforlogixs.R
import com.isaacdelosreyes.valorantforlogixs.core.presentation.ErrorScreen
import com.isaacdelosreyes.valorantforlogixs.core.presentation.ValorantLoader
import com.isaacdelosreyes.valorantforlogixs.ui.theme.BlackGray
import com.isaacdelosreyes.valorantforlogixs.ui.theme.Tugnsten

@Composable
fun MapsScreen(viewModel: MapsViewModel = hiltViewModel(), navigateToMapDetail: (String) -> Unit) {

    val state = viewModel.state

    if (state.showErrorScreen) {
        showErrorScreen {
            viewModel.getMaps()
        }

    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BlackGray)
        ) {
            if (state.showLoaderComponent) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(
                        top = 20.dp,
                        bottom = 10.dp,
                        start = 10.dp,
                        end = 10.dp
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.maps) {

                        if (!state.maps.isNullOrEmpty()) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { navigateToMapDetail(it.uuid) }
                            ) {

                                SubcomposeAsyncImage(
                                    model = it.splash,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize(),
                                    loading = {
                                        ValorantLoader(
                                            color = MaterialTheme.colorScheme.onPrimary
                                        )
                                    }
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

                        } else {
                            showErrorScreen {
                                viewModel.getMaps()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun showErrorScreen(clickOnRetryButton: () -> Unit) {
    ErrorScreen(
        errorMessage = stringResource(id = R.string.default_error),
        showBackButton = false,
        clickOnRetryButton = {
            clickOnRetryButton()
        },
        modifier = Modifier.background(BlackGray)
    )
}