package com.ntech.weedwhiz.datalayer.repository

import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.model.AuthenticationModel
import com.ntech.weedwhiz.datalayer.model.ConfigModel
import com.ntech.weedwhiz.datalayer.model.ConfigRequest
import com.ntech.weedwhiz.datalayer.model.HistoryModel
import kotlinx.coroutines.flow.Flow


interface MonitoringRepository {

    val waterLevelFlow: Flow<AppResponse<Int>>

}