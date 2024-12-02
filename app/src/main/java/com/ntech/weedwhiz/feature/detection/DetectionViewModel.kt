package com.ntech.weedwhiz.feature.detection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.implementation.repository.DetectionRepositoryImpl
import com.ntech.weedwhiz.datalayer.model.DetectionModel
import com.ntech.weedwhiz.datalayer.repository.DetectionRepository
import kotlinx.coroutines.launch

class DetectionViewModel(
    private val detectionRepository: DetectionRepository
) : ViewModel() {


    private val _detectionLiveData = MutableLiveData<AppResponse<List<DetectionModel>>>()
    val detectionLiveData: LiveData<AppResponse<List<DetectionModel>>> get() = _detectionLiveData

    suspend fun fetchDetections() {
        viewModelScope.launch {
            _detectionLiveData.apply {
                postValue(AppResponse.Loading)
                val result = detectionRepository.getDetections()
                postValue(result)
            }
        }
    }

}