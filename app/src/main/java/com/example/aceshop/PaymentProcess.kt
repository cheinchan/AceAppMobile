package com.example.aceshop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun Process(modifier: Modifier,navController: NavController){
    // State to track whether the delay has passed
    var showText by remember { mutableStateOf(false) }
    var showConfirmation by remember { mutableStateOf(false) }
    if (showConfirmation){
        com.example.aceshop.ui.theme.PaymentConfirmation (onDismiss = {showConfirmation = false}, navController = navController)
    }

    // Launch effect to wait for 5 seconds before showing the text
    LaunchedEffect(Unit) {
        delay(5000)
        showConfirmation = true
    }

        // Your loading image or text
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painterResource(id = R.drawable.loading_page), contentDescription ="processImage" )
            Text(text = " Payment Processing....", fontSize = 20.sp)

        }
}

@Preview
@Composable
fun ProcessPreview(){
    Process(modifier =Modifier, navController = rememberNavController())
}