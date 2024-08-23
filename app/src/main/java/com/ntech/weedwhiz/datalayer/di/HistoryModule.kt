package com.ntech.weedwhiz.datalayer.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.implementation.repository.ConfigRepositoryImpl
import com.ntech.weedwhiz.datalayer.implementation.repository.HistoryRepositoryImpl
import com.ntech.weedwhiz.datalayer.repository.ConfigRepository
import com.ntech.weedwhiz.datalayer.repository.HistoryRepository


object HistoryModule {
    fun provideHistoryRef(): CollectionReference {
        return Firebase.firestore.collection("history")
    }

    fun provideHistoryRepository(
        historyRef: CollectionReference,
        dataStorage: DataStorage,
    ): HistoryRepository {
        return HistoryRepositoryImpl(historyRef, dataStorage)
    }
}