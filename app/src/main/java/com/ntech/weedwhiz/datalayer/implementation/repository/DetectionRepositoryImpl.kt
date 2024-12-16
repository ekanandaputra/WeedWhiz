package com.ntech.weedwhiz.datalayer.implementation.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.model.DetectionModel
import com.ntech.weedwhiz.datalayer.model.HistoryModel
import com.ntech.weedwhiz.datalayer.repository.DetectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class DetectionRepositoryImpl(
    private val detectionRef: CollectionReference,
    private val dataStorage: DataStorage
) : DetectionRepository {

    override suspend fun getDetections(): AppResponse<List<DetectionModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val querySnapshot = detectionRef
                    .orderBy("detection_at", Query.Direction.DESCENDING)
                    .get()
                    .await()
                if (querySnapshot.isEmpty) {
                    return@withContext AppResponse.Empty
                } else {
                    val historyList = querySnapshot.documents.map { document ->
                        val desc: String = document.getString("desc") ?: ""
                        val detectionAt = document.get("detection_at") as? Timestamp
                        val detectionImage: String = document.getString("detection_image") ?: ""
                        val image: String = document.getString("image") ?: ""

                        DetectionModel(
                            desc = desc,
                            detectionAt = detectionAt,
                            detectionImage = detectionImage,
                            image = image,
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