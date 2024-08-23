package com.ntech.weedwhiz.datalayer.implementation.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.ntech.weedwhiz.core.utils.AppResponse
import com.ntech.weedwhiz.core.utils.DataStorage
import com.ntech.weedwhiz.datalayer.repository.MonitoringRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class MonitoringRepositoryImpl(
    private val monitoringRef: CollectionReference,
    private val dataStorage: DataStorage
) : MonitoringRepository {

    private var listenerRegistration: ListenerRegistration? = null
    private val _waterLevelFlow = MutableSharedFlow<AppResponse<Int>>(replay = 1)
    override val waterLevelFlow: Flow<AppResponse<Int>> get() = _waterLevelFlow.asSharedFlow()

    init {
        observeWaterLevelChanges()
    }

    private fun observeWaterLevelChanges() {
        listenerRegistration?.remove()

        listenerRegistration = monitoringRef.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                _waterLevelFlow.tryEmit(AppResponse.Error(exception.toString()))
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                val document = snapshot.documents[0]
                val tankVolume = document.get("waterLevel") ?: Any()
                Log.d("TAG", "observeWaterLevelChanges: " + tankVolume)
                _waterLevelFlow.tryEmit(AppResponse.Success(convertToInt(tankVolume) ?: 0))
            } else {
                _waterLevelFlow.tryEmit(AppResponse.Error("No documents found"))
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
}