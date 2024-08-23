package com.ntech.weedwhiz.feature.config

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
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
import com.ntech.theyardhub.feature.auth.ConfigViewModel
import com.ntech.weedwhiz.core.ButtonHeight
import com.ntech.weedwhiz.core.ButtonType
import com.ntech.weedwhiz.core.RouteName
import com.ntech.weedwhiz.core.component.GeneralButton
import com.ntech.weedwhiz.core.component.LoadingDialog
import com.ntech.weedwhiz.core.component.RoundedEditField
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.core.theme.colorPrimary
import com.ntech.weedwhiz.core.utils.AppResponse
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigScreen(navController: NavController) {

    val coroutineScope = rememberCoroutineScope()
    val viewModel: ConfigViewModel = get()
    val mContext = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.fetchConfig()
    }

    val configState = viewModel.configLiveData.observeAsState().value

    val showDialog = remember { mutableStateOf(false) }

    when (configState) {
        is AppResponse.Loading -> {
            showDialog.value = true
        }

        is AppResponse.Empty -> {
            showDialog.value = false
        }

        is AppResponse.Success -> {
            showDialog.value = false
            viewModel.setPictureIntervalState(TextFieldValue(configState.data.pictureInterval.toString()))
            viewModel.setThresholdAccuracyState(TextFieldValue(configState.data.thresholdAccuracy.toString()))
            viewModel.setSprayVolume(TextFieldValue(configState.data.sprayVolume.toString()))
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Pengaturan",
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
        bottomBar = {
            BottomAppBar(
                containerColor = White,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {

            }
        }
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
            RoundedEditField(
                title = "Threshold Akurasi",
                value = viewModel.thresholdAccuracyState.value,
                onValueChange = {
                    viewModel.setThresholdAccuracyState(it)

                },
                hint = "Masukkan threshold akurasi"
            )
            Spacer(modifier = Modifier.height(16.dp))
            RoundedEditField(
                title = "Interval Pengambilan Foto",
                value = viewModel.pictureIntervalState.value,
                onValueChange = {
                    viewModel.setPictureIntervalState(it)
                },
                hint = "Pilih Interval Pengambilan Foto"
            )
            Spacer(modifier = Modifier.height(16.dp))
            RoundedEditField(
                title = "Volume Penyemprotan",
                value = viewModel.sprayVolumeState.value,
                onValueChange = {
                    viewModel.setSprayVolume(it)
                },
                hint = "Masukkan volume penyemprotan"
            )
            Spacer(modifier = Modifier.height(32.dp))
            GeneralButton(
                onButtonClicked = {
                    coroutineScope.launch {
                        viewModel.saveConfig()
                    }
                },
                label = "Simpan",
                buttonType = ButtonType.PRIMARY,
                buttonHeight = ButtonHeight.MEDIUM,
            )
        }
    }
}