package com.ntech.weedwhiz.feature.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.model.HistoryModel
import com.ntech.weedwhiz.datalayer.repository.HistoryRepository
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyRepository: HistoryRepository
) : ViewModel() {


    private val _historyLiveData = MutableLiveData<AppResponse<List<HistoryModel>>>()
    val historyLiveData: LiveData<AppResponse<List<HistoryModel>>> get() = _historyLiveData

    suspend fun fetchHistory() {
        viewModelScope.launch {
            _historyLiveData.apply {
                postValue(AppResponse.Loading)
                val result = historyRepository.getHistory()
                postValue(result)
            }
        }
    }

}