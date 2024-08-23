package com.ntech.theyardhub.feature.auth

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.model.ConfigModel
import com.ntech.weedwhiz.datalayer.model.ConfigRequest
import com.ntech.weedwhiz.datalayer.repository.ConfigRepository
import kotlinx.coroutines.launch

class ConfigViewModel(
    private val configRepository: ConfigRepository
) : ViewModel() {

    val thresholdAccuracyState = mutableStateOf(TextFieldValue(""))
    val pictureIntervalState = mutableStateOf(TextFieldValue(""))
    val sprayVolumeState = mutableStateOf(TextFieldValue(""))

    private val _configLiveData = MutableLiveData<AppResponse<ConfigModel>>()
    val configLiveData: LiveData<AppResponse<ConfigModel>> get() = _configLiveData

    suspend fun fetchConfig() {
        viewModelScope.launch {
            _configLiveData.apply {
                postValue(AppResponse.Loading)
                val result = configRepository.getConfig()
                postValue(result)
            }
        }
    }

    suspend fun saveConfig() {
        viewModelScope.launch {
            _configLiveData.apply {
                postValue(AppResponse.Loading)
                val result = configRepository.saveConfig(
                    ConfigRequest(
                        thresholdAccuracy = thresholdAccuracyState.value.text.toDoubleOrNull()
                            ?: 0.0,
                        sprayVolume = sprayVolumeState.value.text,
                        pictureInterval = pictureIntervalState.value.text,
                    )
                )
                postValue(result)
            }
        }
    }

    fun setThresholdAccuracyState(newValue: TextFieldValue) {
        thresholdAccuracyState.value = newValue
    }

    fun setPictureIntervalState(newValue: TextFieldValue) {
        pictureIntervalState.value = newValue
    }

    fun setSprayVolume(newValue: TextFieldValue) {
        sprayVolumeState.value = newValue
    }
}