package com.ntech.weedwhiz.feature.detection

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Timestamp
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.core.theme.colorPrimary
import com.ntech.weedwhiz.core.utils.convertTimestampToFormattedDate
import com.ntech.weedwhiz.core.utils.formatFirebaseTimestampToDate
import com.ntech.weedwhiz.datalayer.model.DetectionModel

@Composable
fun DetectionItem(data: DetectionModel) {
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
                    text = formatFirebaseTimestampToDate(data.detectionAt ?: Timestamp(0, 0)),
                    modifier = Modifier.padding(10.dp),
                    style = Typography.titleSmall.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = White
                    ),
                )
            }
            AsyncImage(
                model = data.detectionImage,
                contentDescription = "Image from URL",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.FillHeight
            )
        }

    }
}

@Preview
@Composable
fun DetectionItemPreview() {
    DetectionItem(DetectionModel())
}