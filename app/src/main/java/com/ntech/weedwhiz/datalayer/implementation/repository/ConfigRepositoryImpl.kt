package com.ntech.weedwhiz.datalayer.implementation.repository

import com.google.firebase.firestore.CollectionReference
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.model.ConfigModel
import com.ntech.weedwhiz.datalayer.model.ConfigRequest
import com.ntech.weedwhiz.datalayer.repository.ConfigRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ConfigRepositoryImpl(
    private val configRef: CollectionReference,
    private val dataStorage: DataStorage
) : ConfigRepository {
    override suspend fun getConfig(): AppResponse<ConfigModel> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = configRef.get().await()
                if (querySnapshot.isEmpty) {
                    return@withContext AppResponse.Empty
                } else {
                    val data = querySnapshot.documents.first()

                    val uuid: String = data.get("uuid") as? String ?: ""
                    val thresholdAccuracy = data.get("thresholdAccuracy")
                    val pictureInterval = data.get("pictureInterval")
                    val sprayVolume = data.get("sprayVolume")
                    return@withContext AppResponse.Success(
                        ConfigModel(
                            uuid = uuid,
                            thresholdAccuracy = thresholdAccuracy?.let { convertToDouble(it) }
                                ?: 0.0,
                            pictureInterval = pictureInterval?.let { convertToInt(it) } ?: 0,
                            sprayVolume = sprayVolume?.let { convertToInt(it) } ?: 0,
                        )
                    )
                }
            } catch (e: Exception) {
                return@withContext AppResponse.Error(e.toString())
            }
        }
    }

    override suspend fun saveConfig(request: ConfigRequest): AppResponse<ConfigModel> {
        return withContext(Dispatchers.IO) {
            try {
                suspendCancellableCoroutine { continuation ->
                    configRef.document("Yj3WZ4RKNnrjLkMWHEPS").update(
                        mapOf(
                            "thresholdAccuracy" to request.thresholdAccuracy,
                            "pictureInterval" to request.pictureInterval,
                            "sprayVolume" to request.sprayVolume
                        )
                    )
                        .addOnSuccessListener { documentReference ->
                            continuation.resume(documentReference)
                        }
                        .addOnFailureListener { e ->
                            continuation.resumeWithException(e)
                        }
                }

                AppResponse.Success(
                    ConfigModel(
                        thresholdAccuracy = request.thresholdAccuracy,
                        pictureInterval = request.pictureInterval.toIntOrNull() ?: 0,
                        sprayVolume = request.sprayVolume.toIntOrNull() ?: 0,
                    )
                )
            } catch (e: Exception) {
                return@withContext AppResponse.Error(e.toString())
            }
        }
    }

    private fun convertToInt(anyValue: Any): Int? {
        return when (anyValue) {
            is Int -> anyValue
            is String -> anyValue.toIntOrNull()
            else -> null
        }
    }

    private fun convertToDouble(anyValue: Any): Double? {
        return when (anyValue) {
            is Double -> anyValue
            is String -> anyValue.toDoubleOrNull()
            else -> null
        }
    }

}