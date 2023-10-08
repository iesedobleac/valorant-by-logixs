package com.isaacdelosreyes.valorantforlogixs.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isaacdelosreyes.valorantforlogixs.R
import com.isaacdelosreyes.valorantforlogixs.ui.theme.RobotoFamily
import com.isaacdelosreyes.valorantforlogixs.ui.theme.Tugnsten

@Composable
fun ErrorScreen(
    errorMessage: String,
    clickOnRetryButton: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 60.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.no_connection),
            contentDescription = "",
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .weight(1f)
            )

            Text(
                text = "Atención".uppercase(),
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

        Text(
            text = errorMessage,
            fontSize = 16.sp,
            fontFamily = RobotoFamily,
            color = Color.White,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { clickOnRetryButton() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Reintentar", fontFamily = RobotoFamily)
        }
    }
}