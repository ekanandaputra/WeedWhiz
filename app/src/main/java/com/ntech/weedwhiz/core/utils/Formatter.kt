package com.ntech.weedwhiz.core.utils

import com.google.firebase.Timestamp
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Number?.toRupiahFormat(useCurrencySymbol: Boolean = true): String {
    if (this == null)
        if (useCurrencySymbol)
            return "Rp "

    val formatRupiah = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
    val result =
        formatRupiah
            .format(this)
            .replace(",00", "")
            .replace("Rp", "Rp")

    return if (!useCurrencySymbol) result.replace("Rp", "") else result
}

fun convertTimestampToFormattedDate(timestamp: Long): String {
    // Define the desired format
    val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    // Create a Date object from the timestamp
    val date = Date(timestamp)
    // Format the date
    return sdf.format(date)
}

fun formatFirebaseTimestampToDate(timestamp: Timestamp): String {
    // Convert Timestamp to Date
    val date = timestamp.toDate()
    // Define the desired format
    val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale.getDefault())
    // Format the date
    return sdf.format(date)
}