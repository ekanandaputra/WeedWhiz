package com.ntech.weedwhiz.datalayer.model

import com.google.firebase.Timestamp

data class HistoryModel(
    val dateTime: Timestamp = Timestamp(0, 0),
    val duration: Int = 0,
    val tankVolume: Int = 0,
)
