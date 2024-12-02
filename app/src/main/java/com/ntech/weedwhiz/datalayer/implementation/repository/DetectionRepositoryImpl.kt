package com.ntech.weedwhiz.datalayer.implementation.repository

import com.google.firebase.firestore.CollectionReference
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.model.DetectionModel
import com.ntech.weedwhiz.datalayer.repository.DetectionRepository

class DetectionRepositoryImpl(
    private val detectionRef: CollectionReference,
    private val dataStorage: DataStorage
) : DetectionRepository {

    override suspend fun getDetections(): AppResponse<List<DetectionModel>> {
        TODO("Not yet implemented")
    }

}