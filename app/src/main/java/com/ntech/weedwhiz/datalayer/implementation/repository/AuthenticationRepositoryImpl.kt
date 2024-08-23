package com.ntech.weedwhiz.datalayer.implementation.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.model.AuthenticationModel
import com.ntech.weedwhiz.datalayer.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.math.log

class AuthenticationRepositoryImpl(
    private val authRef: CollectionReference,
    private val dataStorage: DataStorage
) :
    AuthenticationRepository {
    override suspend fun postLogin(
        username: String,
        password: String
    ): AppResponse<AuthenticationModel> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = authRef
                    .whereEqualTo("username", username)
                    .whereEqualTo("password", password)
                    .get().await()
                if (querySnapshot.isEmpty) {
                    return@withContext AppResponse.Empty
                } else {
                    val data = querySnapshot.documents.first()

                    val userName: String = data.get("name") as? String ?: ""
                    val userUuid: String = data.get("uuid") as? String ?: ""
                    return@withContext AppResponse.Success(
                        AuthenticationModel(
                            id = userUuid,
                            name = userName
                        )
                    )
                }


            } catch (e: Exception) {
                return@withContext AppResponse.Error(e.toString())
            }
        }
    }

    override suspend fun getUsername(): String {
        dataStorage.userName = "tes"
        return dataStorage.userName
    }
}