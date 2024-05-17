package com.example.aceshop

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aceshop.data.Racket
import com.example.aceshop.data.Shuttlecock
import com.example.aceshop.ui.theme.Screen
import com.example.aceshop.viewmodel.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun SeeAll(modifier: Modifier,navController: NavController,racketViewModel:ViewModel,shuttlecockViewModel: ViewModel){
    val racketListState = remember { mutableStateOf<List<Racket>>(emptyList()) }
    val shuttlecockListState = remember { mutableStateOf<List<Shuttlecock>>(emptyList()) }
    val combinedList = remember { mutableStateOf<List<Any>>(emptyList()) }

    val db = Firebase.firestore


    LaunchedEffect(key1 = true) {
        racketViewModel.readRacket(db) { racketList ->
            racketListState.value = racketList
        }
    }
    LaunchedEffect(key1 = true) {
        shuttlecockViewModel.readShuttlecock(db) { shuttlecockList ->
            shuttlecockListState.value = shuttlecockList
        }
    }
    LaunchedEffect(racketListState.value, shuttlecockListState.value) {
        val combined = racketListState.value + shuttlecockListState.value
        combinedList.value = combined.shuffled()
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
                text = "All Product",
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
        item {
            combinedList.value.chunked(2).forEach { rowItems ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.padding(bottom = 55.dp)
                ) {
                    rowItems.forEach { item ->
                        when (item) {
                            is Racket -> {
                                ProductItemDetails(
                                    imageUrl = item.racket_image,
                                    productName = item.racket_details,
                                    productDescription = "High-quality racket",
                                    productPrice = "RM ${item.racket_price}",
                                    onClick = {
                                        racketViewModel.selectedRacketId = item.racket_id
                                        navController.navigate(Screen.ItemSelectedRacket.route)
                                    }
                                )
                            }
                            is Shuttlecock -> {
                                ProductItemDetails(
                                    imageUrl = item.shuttlecock_image,
                                    productName = item.shuttlecock_details,
                                    productDescription = "High-quality shuttlecock",
                                    productPrice = "RM ${item.shuttlecock_price}",
                                    onClick = {
                                        shuttlecockViewModel.selectedShuttlecockId = item.shuttlecock_id
                                        navController.navigate(Screen.ItemSelectedShuttlecock.route)
                                    }
                                )
                            }
                        }
                    }
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.width(20.dp)) // To balance the row if there's only one item
                    }
                }
            }
        }

    }



}