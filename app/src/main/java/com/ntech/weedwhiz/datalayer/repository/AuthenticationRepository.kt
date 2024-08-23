package com.ntech.weedwhiz.datalayer.repository

import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.datalayer.model.AuthenticationModel


interface AuthenticationRepository {

    suspend fun postLogin(username: String, password: String): AppResponse<AuthenticationModel>?

    suspend fun getUsername(): String

}