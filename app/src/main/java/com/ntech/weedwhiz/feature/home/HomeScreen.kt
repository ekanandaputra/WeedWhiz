package com.ntech.weedwhiz.feature.home

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ntech.weedwhiz.R
import com.ntech.weedwhiz.core.ButtonHeight
import com.ntech.weedwhiz.core.ButtonType
import com.ntech.weedwhiz.core.component.GeneralButton
import com.ntech.weedwhiz.core.theme.Gray
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.core.theme.colorPrimary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val usernameState = remember { mutableStateOf(TextFieldValue("")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val image: Painter = painterResource(id = R.drawable.icon)
                        Image(
                            painter = image,
                            contentDescription = "App Logo",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            "WeedWhiz",
                            modifier = Modifier.padding(start = 8.dp),
                            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = White
                )
            )
        },
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
        ) {

        }
    }
}

@Composable
fun HeaderHome() {
    Row {
        Column {
            Text(text = "Hello Ekananda", style = Typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We bring the best for you",
                style = Typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = colorPrimary
                )
            )
        }
    }
}

@Composable
fun ArticleHome() {
    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Hello Ekananda", style = Typography.titleMedium)
            Text(text = "Lihat Semua", style = Typography.labelSmall)
        }

    }
}

@Composable
fun ArticleItem() {
    Box(
        modifier = Modifier
            .border(
                BorderStroke(0.5.dp, Gray),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            val image: Painter = painterResource(id = R.drawable.icon)
            Image(
                painter = image,
                contentDescription = "App Logo",
                modifier = Modifier.size(72.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
            ) {
                Text(text = "Hello Ekananda", style = Typography.titleMedium)
                Text(
                    text = "Lihat Semua",
                    style = Typography.labelSmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .padding(top = 8.dp)
                ) {
                    GeneralButton(
                        onButtonClicked = { /*TODO*/ },
                        label = "Lihat Detail",
                        buttonType = ButtonType.PRIMARY,
                        buttonHeight = ButtonHeight.SMALL,
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}