package com.example.aceshop

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aceshop.ui.theme.Screen

@Composable
fun Order(modifier: Modifier,navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),

        ) {
        Row(
            horizontalArrangement = Arrangement.Start, // Align elements to start (left)
            verticalAlignment = Alignment.CenterVertically // Align elements vertically in the center
        ) {
            Column(horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 40.dp, end = 75.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(
                            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { navController.navigate(Screen.ShoppingHomePage.route )} // Navigate back on click
                )
            }


            Text(
                text = "Order",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(start = 8.dp) // Add padding between the image and text
            )
        }
    }
}