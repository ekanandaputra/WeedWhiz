package com.ntech.weedwhiz.feature.monitoring

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ntech.theyardhub.feature.auth.MonitoringViewModel
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.core.theme.colorPrimary
import com.ntech.weedwhiz.core.utils.AppResponse
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonitoringScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val viewModel: MonitoringViewModel = get()
    val mContext = LocalContext.current
    var waterLevel = 0

    val usernameState = remember { mutableStateOf(TextFieldValue("")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }


    val monitoringState = viewModel.monitoringLiveData.observeAsState().value

    when (monitoringState) {
        is AppResponse.Loading -> {
        }

        is AppResponse.Empty -> {
        }

        is AppResponse.Success -> {
            waterLevel = monitoringState.data
        }

        else -> {
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Monitoring",
                            modifier = Modifier.padding(start = 8.dp),
                            style = Typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = White
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorPrimary
                )
            )
        },
        containerColor = White,
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
                    .background(
                        color = colorPrimary,
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Card(
                    modifier = Modifier
                        .padding(top = 32.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Volume Tangki",
                            style = Typography.titleMedium.copy(color = colorPrimary)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(
                            text = waterLevel.toString() + "mL",
                            style = Typography.titleLarge.copy(
                                fontSize = 48.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }
                }
            }
        }

    }
}
