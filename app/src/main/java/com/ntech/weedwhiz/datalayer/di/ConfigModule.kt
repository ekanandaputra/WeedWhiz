package com.ntech.weedwhiz.datalayer.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.implementation.repository.ConfigRepositoryImpl
import com.ntech.weedwhiz.datalayer.repository.ConfigRepository


object ConfigModule {
    fun provideConfigRef(): CollectionReference {
        return Firebase.firestore.collection("configs")
    }

    fun provideConfigRepository(
        configRef: CollectionReference,
        dataStorage: DataStorage,
    ): ConfigRepository {
        return ConfigRepositoryImpl(configRef, dataStorage)
    }
}