package com.ntech.weedwhiz.datalayer.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.implementation.repository.ConfigRepositoryImpl
import com.ntech.weedwhiz.datalayer.implementation.repository.HistoryRepositoryImpl
import com.ntech.weedwhiz.datalayer.implementation.repository.MonitoringRepositoryImpl
import com.ntech.weedwhiz.datalayer.repository.ConfigRepository
import com.ntech.weedwhiz.datalayer.repository.HistoryRepository
import com.ntech.weedwhiz.datalayer.repository.MonitoringRepository

object DetectionModule {
    fun provideDetectionRef(): CollectionReference {
        return Firebase.firestore.collection("detections")
    }

    fun provideDetectionRepository(
        detectionRef: CollectionReference,
        dataStorage: DataStorage,
    ): MonitoringRepository {
        return MonitoringRepositoryImpl(detectionRef, dataStorage)
    }
}