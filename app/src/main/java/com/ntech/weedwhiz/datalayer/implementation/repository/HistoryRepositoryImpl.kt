package com.ntech.weedwhiz.datalayer.implementation.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.model.ConfigModel
import com.ntech.weedwhiz.datalayer.model.ConfigRequest
import com.ntech.weedwhiz.datalayer.model.HistoryModel
import com.ntech.weedwhiz.datalayer.repository.ConfigRepository
import com.ntech.weedwhiz.datalayer.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class HistoryRepositoryImpl(
    private val historyRef: CollectionReference,
    private val dataStorage: DataStorage
) : HistoryRepository {

    private fun convertToInt(anyValue: Any): Int? {
        return when (anyValue) {
            is Int -> anyValue
            is String -> anyValue.toIntOrNull()
            else -> null
        }
    }

    private fun timestampToString(timestamp: Timestamp): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = timestamp.toDate()
        return dateFormat.format(date)
    }

    override suspend fun getHistory(): AppResponse<List<HistoryModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = historyRef.get().await()
                if (querySnapshot.isEmpty) {
                    return@withContext AppResponse.Empty
                } else {
                    val historyList = querySnapshot.documents.map { document ->
                        val uuid: String = document.getString("uuid") ?: ""
                        val dateTime =
                            (document.get("dateTime") as? Timestamp)?.let { timestampToString(it) }
                        val tankVolume = document.get("tankVolume")
                        val sprayVolume = document.get("sprayVolume")

                        HistoryModel(
                            uuid = uuid,
                            sprayVolume = sprayVolume?.let { convertToInt(it) } ?: 0,
                            tankVolume = tankVolume?.let { convertToInt(it) } ?: 0,
                            dateTime = dateTime ?: ""
                        )
                    }
                    return@withContext AppResponse.Success(historyList)
                }
            } catch (e: Exception) {
                return@withContext AppResponse.Error(e.toString())
            }
        }
    }

}