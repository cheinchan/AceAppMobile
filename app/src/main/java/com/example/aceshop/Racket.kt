package com.example.aceshop

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.aceshop.data.Racket
import com.example.aceshop.ui.theme.Screen
import com.example.aceshop.viewmodel.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun ShowRacket(modifier: Modifier,navController: NavController,racketViewModel: ViewModel) {
    val racketListState = remember { mutableStateOf<List<Racket>>(emptyList()) }

    val db = Firebase.firestore
    LaunchedEffect(key1 = true) {
        racketViewModel.readRacket(db) { racket ->
            val racketData = racket.toList()
            racketListState.value = racketData
        }
    }

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
                text = "Racket",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(start = 8.dp) // Add padding between the image and text
            )
        }
    }



    LazyColumn(
        modifier = Modifier
            .padding(start = 45.dp, top = 99.dp)
    ) {
        itemsIndexed(racketListState.value) { index, racket ->
            if (index % 2 == 0) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(bottom = 55.dp)
                ) {

                    ProductItemDetails(
                        imageUrl = racket.racket_image,
                        productName = racket.racket_details,
                        productDescription = "High-quality racket", // You can change this to racket description
                        productPrice = "RM ${racket.racket_price}",
                        onClick = {
                            racketViewModel.selectedRacketId = racket.racket_id
                            navController.navigate(Screen.ItemSelectedRacket.route)
                        }// Use racket price here
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    if (index + 1 < racketListState.value.size) {
                        val nextRacket = racketListState.value[index + 1]
                        ProductItemDetails(
                            imageUrl = nextRacket.racket_image,
                            productName = nextRacket.racket_details,
                            productDescription = "High-quality racket", // You can change this to racket description
                            productPrice = "RM ${nextRacket.racket_price}",
                            onClick = {
                                racketViewModel.selectedRacketId = nextRacket.racket_id
                                navController.navigate(Screen.ItemSelectedRacket.route)}// Use racket price here
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowRacketPreview(){

    ShowRacket(modifier = Modifier, navController = rememberNavController(), racketViewModel = ViewModel())
}