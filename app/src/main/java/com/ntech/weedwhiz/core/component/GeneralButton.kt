package com.ntech.weedwhiz.core.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ntech.weedwhiz.core.ButtonHeight
import com.ntech.weedwhiz.core.ButtonType
import com.ntech.weedwhiz.core.theme.Black
import com.ntech.weedwhiz.core.theme.Gray
import com.ntech.weedwhiz.core.theme.Typography
import com.ntech.weedwhiz.core.theme.White
import com.ntech.weedwhiz.core.theme.colorPrimary

@Composable
fun GeneralButton(
    onButtonClicked: () -> Unit,
    label: String,
    buttonType: ButtonType,
    buttonHeight: Dp,
    isEnabled: Boolean = true,
) {
    var buttonColor by remember { mutableStateOf(Color.Unspecified) }
    var textColor by remember { mutableStateOf(Color.Unspecified) }
    var borderColor by remember { mutableStateOf(Color.Unspecified) }
    var textStyle by remember { mutableStateOf(TextStyle.Default) }

    when (buttonType) {
        ButtonType.PRIMARY -> {
            buttonColor = colorPrimary
            textColor = White
            borderColor = colorPrimary
        }

        ButtonType.SECONDARY -> {
            buttonColor = White
            textColor = colorPrimary
            borderColor = colorPrimary
        }
    }

    when (buttonHeight) {
        ButtonHeight.SMALL -> {
            textStyle = Typography.labelSmall
        }

        ButtonHeight.MEDIUM -> {
            textStyle = Typography.labelMedium
        }
    }

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor,
            disabledContainerColor = Gray,
            disabledContentColor = Black,
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .height(buttonHeight)
            .fillMaxSize(),
        border = BorderStroke(1.dp, color = borderColor),
        onClick = onButtonClicked,
        enabled = isEnabled
    ) {
        Text(text = label, style = textStyle)
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPrimaryMedium() {
    GeneralButton(
        onButtonClicked = { },
        label = "Button Primary Medium",
        buttonType = ButtonType.PRIMARY,
        buttonHeight = ButtonHeight.MEDIUM
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonSecondaryMedium() {
    GeneralButton(
        onButtonClicked = { },
        label = "Button Secondary Medium",
        buttonType = ButtonType.SECONDARY,
        buttonHeight = ButtonHeight.MEDIUM
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ButtonPrimarySmall() {
    GeneralButton(
        onButtonClicked = { },
        label = "Button Primary Small",
        buttonType = ButtonType.PRIMARY,
        buttonHeight = ButtonHeight.SMALL
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonSecondarySmall() {
    GeneralButton(
        onButtonClicked = { },
        label = "Button Secondary Small",
        buttonType = ButtonType.SECONDARY,
        buttonHeight = ButtonHeight.SMALL
    )
}