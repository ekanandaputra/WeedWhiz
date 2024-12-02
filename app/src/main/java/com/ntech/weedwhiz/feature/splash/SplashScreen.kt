package com.ntech.weedwhiz.feature.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.ntech.weedwhiz.R
import com.ntech.weedwhiz.core.RouteName.DETECTION_SCREEN
import com.ntech.weedwhiz.core.RouteName.HISTORY_SCREEN
import com.ntech.weedwhiz.core.RouteName.HOME_SCREEN
import com.ntech.weedwhiz.core.RouteName.SPLASH_SCREEN
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun SplashScreen(navController: NavController) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate(DETECTION_SCREEN) {
            popUpTo(SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }

    Scaffold(
        containerColor = White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding() + 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val image: Painter = painterResource(id = R.drawable.logo)
            Image(
                painter = image,
                contentDescription = "App Logo",
                modifier = Modifier.size(128.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Weed Whiz",
                style = Typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
            )
        }
    }
}