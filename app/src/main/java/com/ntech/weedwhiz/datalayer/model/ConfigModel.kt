package com.ntech.weedwhiz.datalayer.model

data class ConfigModel(
    val uuid: String = "",
    val thresholdAccuracy: Double = 0.0,
    val pictureInterval: Int = 0,
    val sprayVolume: Int = 0,
)
