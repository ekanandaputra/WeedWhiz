package com.ntech.weedwhiz.datalayer.model

data class ConfigRequest(
    val thresholdAccuracy: Double = 0.0,
    val pictureInterval: String = "0",
    val sprayVolume: String = "0",
)
