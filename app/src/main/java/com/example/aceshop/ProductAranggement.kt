package com.example.aceshop

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductItemDetails(
    imageUrl: String,
    productName: String,
    productDescription: String,
    productPrice: String,
    onClick: () -> Unit // Lambda function to handle click event
) {

    Box(
        modifier = Modifier
            .requiredWidth(width = 130.dp)
            .requiredHeight(height = 197.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = Uri.parse(imageUrl)),
            contentDescription = "Image",
            modifier = Modifier
                .requiredSize(size = 120.dp)
                .padding(start = 20.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        )
        Text(
            text = productName,
            color = Color.Black.copy(alpha = 0.5f),
            style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(top = 130.dp, start = 20.dp)
                .requiredWidth(width = 119.dp)
        )
        Text(
            text = productPrice,
            color = Color.Black,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(top = 177.dp, start = 20.dp)
        )
    }
}

@Composable
fun ProductCartDetails(
    imageUrl: String,
    productName: String,
    productDescription: String,
    racketId: String,
    productPrice: String,
    onClick: () -> Unit // Lambda function to handle click event
) {
    Box(
        modifier = Modifier
            .requiredWidth(width = 130.dp)
            .requiredHeight(height = 197.dp)
            .clickable { onClick() }
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(model = Uri.parse(imageUrl)),
                contentDescription = "Image",
                modifier = Modifier
                    .requiredSize(size = 120.dp)
                    .padding(start = 20.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            )
            Column {
                Text(
                    text = productName,
                    color = Color.Black.copy(alpha = 0.5f),
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier

                        .padding(top = 130.dp, start = 20.dp)
                        .requiredWidth(width = 119.dp)
                )
                Text(
                    text = productPrice,
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier

                        .padding(top = 177.dp, start = 20.dp)
                )
            }
        }


    }
}



