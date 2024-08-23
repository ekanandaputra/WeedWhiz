package com.ntech.weedwhiz.feature.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.colorPrimary
import com.ntech.weedwhiz.datalayer.model.HistoryModel

@Composable
fun HistoryItem(historyModel: HistoryModel) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.clickable { }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorPrimary),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "10 April 2023, 03:45",
                    modifier = Modifier.padding(10.dp),
                    style = Typography.titleSmall.copy(fontWeight = FontWeight.ExtraBold),
                )
            }
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Volume Penyemprotan: 5 ml", style = Typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Volume Tangki Saat Ini: 100 ml", style = Typography.titleMedium)
        }
    }
}

@Preview
@Composable
fun HistoryItemPreview() {
    HistoryItem(HistoryModel())
}