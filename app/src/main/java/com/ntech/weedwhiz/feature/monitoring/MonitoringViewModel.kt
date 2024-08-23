package com.ntech.theyardhub.feature.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.model.ConfigModel
import com.ntech.weedwhiz.datalayer.model.ConfigRequest
import com.ntech.weedwhiz.datalayer.model.HistoryModel
import com.ntech.weedwhiz.datalayer.repository.ConfigRepository
import com.ntech.weedwhiz.datalayer.repository.HistoryRepository
import com.ntech.weedwhiz.datalayer.repository.MonitoringRepository
import kotlinx.coroutines.launch

class MonitoringViewModel(
    private val monitoringRepository: MonitoringRepository
) : ViewModel() {

    private val _monitoringLiveData = MutableLiveData<AppResponse<Int>>()
    val monitoringLiveData: LiveData<AppResponse<Int>> get() = _monitoringLiveData

    init {
        observeRepository()
    }

    private fun observeRepository() {
        viewModelScope.launch {
            monitoringRepository.waterLevelFlow.collect { response ->
                _monitoringLiveData.postValue(response)
            }
        }
    }

}