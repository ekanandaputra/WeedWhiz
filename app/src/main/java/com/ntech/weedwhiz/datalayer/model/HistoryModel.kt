package com.ntech.weedwhiz.datalayer.model

data class HistoryModel(
    val uuid: String = "",
    val dateTime: String = "",
    val sprayVolume: Int = 0,
    val tankVolume: Int = 0,
)
