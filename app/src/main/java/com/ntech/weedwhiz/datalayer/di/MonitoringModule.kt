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

object MonitoringModule {
    fun provideMonitoringRef(): CollectionReference {
        return Firebase.firestore.collection("monitoring")
    }

    fun provideMonitoringRepository(
        monitoringRef: CollectionReference,
        dataStorage: DataStorage,
    ): MonitoringRepository {
        return MonitoringRepositoryImpl(monitoringRef, dataStorage)
    }
}