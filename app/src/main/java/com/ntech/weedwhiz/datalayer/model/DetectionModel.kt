package com.ntech.weedwhiz.datalayer.model

import com.google.firebase.Timestamp

data class DetectionModel(
    val desc: String = "",
    val detectionAt: Timestamp? = null,
    val detectionImage: String = "",
    val image: String = "",
)
