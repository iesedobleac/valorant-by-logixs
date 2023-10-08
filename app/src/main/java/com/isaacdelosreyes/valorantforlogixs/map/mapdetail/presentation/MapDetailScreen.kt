package com.isaacdelosreyes.valorantforlogixs.map.mapdetail.presentation

import BlurTransformation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.isaacdelosreyes.valorantforlogixs.R
import com.isaacdelosreyes.valorantforlogixs.core.presentation.ErrorScreen
import com.isaacdelosreyes.valorantforlogixs.ui.theme.BlackGray
import com.isaacdelosreyes.valorantforlogixs.ui.theme.RobotoFamily
import com.isaacdelosreyes.valorantforlogixs.ui.theme.Tugnsten
import com.isaacdelosreyes.valorantforlogixs.ui.theme.WhiteBroken

@Composable
fun MapDetailScreen(viewModel: MapDetailViewModel = hiltViewModel(), navigateBack: () -> Unit) {

    val state = viewModel.state
    val map = state.map

    if (state.showErrorScreen) {
        ErrorScreen(
            imageDrawableRes = R.drawable.no_map_information,
            errorMessage = stringResource(id = R.string.no_map_error),
            navigateBack = { navigateBack() },
            clickOnRetryButton = {
                viewModel.getMapInformation()
            },
            modifier = Modifier.background(BlackGray)
        )

    } else {

        if (state.showLoaderComponent) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BlackGray)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary
                )
            }

        } else {

            Column(
                Modifier
                    .fillMaxSize()
                    .background(BlackGray)
            ) {

                Box {

                    val request = ImageRequest.Builder(LocalContext.current)
                        .data(map?.splash)
                        .transformations(
                            listOf(
                                BlurTransformation(
                                    scale = 0.5f,
                                    radius = 25
                                )
                            )
                        )
                        .build()

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {

                        SubcomposeAsyncImage(
                            model = request,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {


                            Box(modifier = Modifier.fillMaxSize()) {

                                Column(modifier = Modifier.align(Alignment.Center)) {

                                    Text(
                                        text = map?.displayName.orEmpty().uppercase(),
                                        fontFamily = Tugnsten,
                                        fontSize = 90.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Image(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "",
                                            colorFilter = ColorFilter.tint(
                                                Color.White
                                            ),
                                            modifier = Modifier.size(16.dp)
                                        )

                                        Text(
                                            text = map?.coordinates.orEmpty().uppercase(),
                                            fontFamily = RobotoFamily,
                                            fontSize = 16.sp,
                                            color = Color.White,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    IconButton(
                        onClick = { navigateBack() },
                        modifier = Modifier.padding(
                            start = 20.dp,
                            top = 20.dp
                        ),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 25.dp, vertical = 20.dp)
                    ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Image(
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier.size(22.dp)
                            )

                            Text(
                                text = stringResource(id = R.string.description).uppercase(),
                                fontSize = 26.sp,
                                fontFamily = Tugnsten,
                                color = Color.White,
                                modifier = Modifier.padding(start = 10.dp)
                            )

                            Divider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = map?.narrativeDescription.orEmpty(),
                            fontSize = 14.sp,
                            fontFamily = RobotoFamily,
                            color = WhiteBroken,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 8,
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Divider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier
                                    .weight(1f)
                            )

                            Text(
                                text = stringResource(id = R.string.map).uppercase(),
                                fontSize = 26.sp,
                                fontFamily = Tugnsten,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )

                            Divider(
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        SubcomposeAsyncImage(
                            model = map?.displayIcon,
                            contentDescription = "",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxSize()
                                .aspectRatio(1f)
                        )
                    }
                }
            }
        }
    }
}