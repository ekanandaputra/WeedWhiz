package com.ntech.weedwhiz.datalayer.repository

import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.model.AuthenticationModel
import com.ntech.weedwhiz.datalayer.model.ConfigModel
import com.ntech.weedwhiz.datalayer.model.ConfigRequest


interface ConfigRepository {

    suspend fun getConfig(): AppResponse<ConfigModel>

    suspend fun saveConfig(request: ConfigRequest): AppResponse<ConfigModel>

}