package com.ntech.weedwhiz.feature.history

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ntech.theyardhub.feature.auth.ConfigViewModel
import com.ntech.theyardhub.feature.auth.HistoryViewModel
import com.ntech.weedwhiz.R
import com.ntech.weedwhiz.core.ButtonHeight
import com.ntech.weedwhiz.core.ButtonType
import com.ntech.weedwhiz.core.component.GeneralButton
import com.ntech.weedwhiz.core.component.LoadingDialog
import com.ntech.weedwhiz.core.theme.Gray
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.core.theme.colorPrimary
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.model.HistoryModel
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val viewModel: HistoryViewModel = get()
    val mContext = LocalContext.current
    var itemsList = arrayListOf<HistoryModel>()

    LaunchedEffect(key1 = true) {
        viewModel.fetchHistory()
    }

    val historyState = viewModel.historyLiveData.observeAsState().value

    val showDialog = remember { mutableStateOf(false) }

    when (historyState) {
        is AppResponse.Loading -> {
            showDialog.value = true
        }

        is AppResponse.Empty -> {
            showDialog.value = false
        }

        is AppResponse.Success -> {
            showDialog.value = false
            itemsList = historyState.data as ArrayList<HistoryModel>
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
                        val image: Painter = painterResource(id = R.drawable.icon)
                        Image(
                            painter = image,
                            contentDescription = "App Logo",
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            "YardHub",
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
                    HistoryItem(historyModel = itemsList[index])
                }
            }
        }
    }
}
