package com.ntech.weedwhiz.datalayer.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.implementation.repository.AuthenticationRepositoryImpl
import com.ntech.weedwhiz.datalayer.repository.AuthenticationRepository


object AuthenticationModule {
    fun provideAuthRef(): CollectionReference {
        return Firebase.firestore.collection("users")
    }

    fun provideAuthRepository(
        authRef: CollectionReference,
        dataStorage: DataStorage,
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(authRef, dataStorage)
    }
}