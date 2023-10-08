package com.isaacdelosreyes.valorantforlogixs.agent.agentdetail.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.isaacdelosreyes.valorantforlogixs.R
import com.isaacdelosreyes.valorantforlogixs.core.presentation.ErrorScreen
import com.isaacdelosreyes.valorantforlogixs.core.presentation.ValorantLoader
import com.isaacdelosreyes.valorantforlogixs.ui.theme.BlackGray
import com.isaacdelosreyes.valorantforlogixs.ui.theme.RobotoFamily
import com.isaacdelosreyes.valorantforlogixs.ui.theme.SherpaBlue50
import com.isaacdelosreyes.valorantforlogixs.ui.theme.Tugnsten
import com.isaacdelosreyes.valorantforlogixs.ui.theme.WhiteBroken

@Composable
fun AgentDetailScreen(viewModel: AgentDetailViewModel = hiltViewModel(), navigateBack: () -> Unit) {

    val state = viewModel.state
    val agent = state.agent

    val colors = agent?.backgroundGradientColors?.map { color ->
        val colorAndroid = "#$color".toColorInt()
        Color(colorAndroid)
    }

    if (state.showErrorScreen) {
        ErrorScreen(
            imageDrawableRes = R.drawable.no_information,
            errorMessage = stringResource(id = R.string.no_agent_error),
            navigateBack = { navigateBack() },
            clickOnRetryButton = {
                viewModel.getAgentInformation()
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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            endY = 0.5f
                        )
                    )
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        if (colors?.isNotEmpty() == true) {
                            Brush.verticalGradient(colors = colors)

                        } else {
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White,
                                    Color.Transparent
                                )
                            )
                        }
                    )
            ) {

                Column(modifier = Modifier.fillMaxSize()) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                    ) {

                        IconButton(
                            onClick = { navigateBack() },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(start = 20.dp, top = 20.dp),
                            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "",
                            )
                        }

                        SubcomposeAsyncImage(
                            model = agent?.fullPortrait,
                            contentDescription = "",
                            loading = { ValorantLoader() }
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(start = 20.dp)
                        ) {

                            Text(
                                text = agent?.displayName.orEmpty().uppercase(),
                                fontFamily = Tugnsten,
                                fontSize = 90.sp,
                                color = Color.White,
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 20.dp)
                            ) {

                                SubcomposeAsyncImage(
                                    model = agent?.role?.displayIcon,
                                    contentDescription = "",
                                    modifier = Modifier.size(10.dp)
                                )

                                Text(
                                    text = agent?.role?.displayName.orEmpty(),
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontFamily = RobotoFamily,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                            .background(SherpaBlue50)
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 25.dp, vertical = 20.dp)
                                .verticalScroll(rememberScrollState())
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
                                    text = stringResource(id = R.string.biography).uppercase(),
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
                                text = agent?.description.orEmpty(),
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
                                    text = stringResource(id = R.string.abilities).uppercase(),
                                    fontSize = 26.sp,
                                    fontFamily = Tugnsten,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.weight(1f)
                                )

                                Divider(
                                    thickness = 1.dp,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier
                                        .weight(1f)
                                )

                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            LazyRow(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                items(agent?.abilities.orEmpty()) {

                                    val isSelected = state.selectedAbility == it

                                    Card(
                                        border = BorderStroke(1.dp, Color.White),
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clickable {
                                                if (!isSelected) viewModel.changeSelectedAbility(it)
                                            },
                                        shape = RoundedCornerShape(6.dp)
                                    ) {
                                        SubcomposeAsyncImage(
                                            model = it.displayIcon,
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(
                                                    if (isSelected) {
                                                        MaterialTheme.colorScheme.secondary
                                                    } else {
                                                        Color.Transparent
                                                    }
                                                )
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = state.selectedAbility?.displayName.orEmpty(),
                                fontSize = 18.sp,
                                color = Color.White,
                                fontFamily = RobotoFamily,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Text(
                                text = state.selectedAbility?.description.orEmpty(),
                                fontSize = 14.sp,
                                color = WhiteBroken,
                                fontFamily = RobotoFamily,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 8,
                            )
                        }
                    }
                }
            }
        }
    }
}