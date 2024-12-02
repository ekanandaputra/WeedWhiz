package com.ntech.weedwhiz.feature.detection

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ntech.weedwhiz.R
import com.ntech.weedwhiz.core.component.LoadingDialog
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.core.theme.colorPrimary
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.model.DetectionModel
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetectionScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val viewModel: DetectionViewModel = get()
    val mContext = LocalContext.current
    var itemsList = arrayListOf<DetectionModel>()

    LaunchedEffect(key1 = true) {
        viewModel.fetchDetections()
    }

    val detectionState = viewModel.detectionLiveData.observeAsState().value

    val showDialog = remember { mutableStateOf(false) }

    when (detectionState) {
        is AppResponse.Loading -> {
            showDialog.value = true
        }

        is AppResponse.Empty -> {
            showDialog.value = false
        }

        is AppResponse.Success -> {
            showDialog.value = false
            itemsList = detectionState.data as ArrayList<DetectionModel>
        }

        else -> {
            showDialog.value = false
        }
    }

    if (showDialog.value) LoadingDialog(setShowDialog = {})

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "WeedWhiz",
                            modifier = Modifier.padding(start = 8.dp),
                            style = Typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold)
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorPrimary
                )
            )
        },
        containerColor = White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(itemsList.size) { index ->
                    DetectionItem(data = itemsList[index])
                }
            }
        }
    }
}