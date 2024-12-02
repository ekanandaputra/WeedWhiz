package com.ntech.weedwhiz.datalayer.implementation.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.model.HistoryModel
import com.ntech.weedwhiz.datalayer.repository.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HistoryRepositoryImpl(
    private val historyRef: CollectionReference,
    private val dataStorage: DataStorage
) : HistoryRepository {

    override suspend fun getHistory(): AppResponse<List<HistoryModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = historyRef
                    .orderBy("date_time", Query.Direction.ASCENDING)
                    .get()
                    .await()
                if (querySnapshot.isEmpty) {
                    return@withContext AppResponse.Empty
                } else {
                    val historyList = querySnapshot.documents.map { document ->
                        val dateTime: Timestamp =
                            document.getTimestamp("date_time") ?: Timestamp(0, 0)
                        val tankVolume: Long = document.getLong("tank_volume") ?: 0
                        val duration: Long = document.getLong("duration") ?: 0

                        HistoryModel(
                            tankVolume = tankVolume.toInt(),
                            dateTime = dateTime,
                            duration = duration.toInt(),
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