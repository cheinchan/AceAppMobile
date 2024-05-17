package com.example.aceshop

import android.net.Uri
import android.view.View
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.aceapps.ui.addRacketProducts
import com.example.aceapps.ui.addShuttlecockProducts
import com.example.aceshop.data.Racket
import com.example.aceshop.data.Shuttlecock
import com.example.aceshop.ui.theme.Screen
import com.example.aceshop.viewmodel.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


@Composable
fun ShoppingHomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    racketViewModel:ViewModel,
    shuttlecockViewModel: ViewModel) {
    val racketListState = remember { mutableStateOf<List<Racket>>(emptyList()) }
    val shuttlecockListState = remember { mutableStateOf<List<Shuttlecock>>(emptyList()) }
    var showDialog by remember { mutableStateOf(false) }
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


if (showDialog){
    com.example.aceshop.ui.theme.AlertDialog (onDismiss = {showDialog = false}, navController = navController)
}

    LazyColumn(modifier =  Modifier.fillMaxSize()
        ) {
        item {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {

            Box(
                modifier = modifier
                    .background(color = Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(start = 22.dp, top = 47.dp)
                        .requiredWidth(width = 177.dp)
                        .requiredHeight(height = 48.dp)
                ) {
                    Text(
                        text = "Discover",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "Find anything what you want!",
                        color = Color.Black.copy(alpha = 0.5f),
                        style = TextStyle(
                            fontSize = 12.sp
                        ),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .padding(top = 26.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.selectcatogory),
                    contentDescription = "selectCatogory",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(top = 40.dp, start = 250.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(
                            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(all = 5.dp)
                        .clickable { showDialog = true }
                )
                Image(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "cartLogo",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(top = 40.dp, start = 315.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(
                            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(all = 5.dp)
                        .clickable { navController.navigate(Screen.Cart.route) }
                )

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(start = 22.dp, top = 120.dp)
                        .requiredWidth(width = 352.dp)
                        .requiredHeight(height = 105.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.forbeauty),
                        contentDescription = "forbeauty",
                        modifier = Modifier
                            .requiredWidth(width = 352.dp)
                            .requiredHeight(height = 105.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                    )
                }

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(top = 252.dp, start = 22.dp)
                        .requiredWidth(width = 113.dp)
                        .requiredHeight(height = 30.dp)
                ) {
                    Text(
                        text = "Categories",
                        color = Color.Black,
                        style = TextStyle(
                            fontSize = 20.sp
                        )
                    )
                }

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                ) {
                    Button(
                        onClick = { navController.navigate(Screen.Racket.route) },
                        modifier = Modifier
                            .padding(start = 22.dp, top = 290.dp)
                        ,
                        colors = ButtonDefaults.buttonColors(Color.White, Color.Black),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(text = "Racket")
                    }
                    Button(
                        onClick = {  navController.navigate(Screen.Shuttlecock.route)},
                        modifier = Modifier
                            .padding(start = 122.dp, top = 290.dp)
                        ,
                        colors = ButtonDefaults.buttonColors(Color.White, Color.Black),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {
                        Text(text = "Shuttlecock")
                    }
                }


            }
        }

    }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
                modifier = modifier.padding(start = 20.dp, top = 13.dp)
            ) {
                items(combinedList.value) { item ->
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

            }
        }

        item { Box(
            modifier = Modifier
                .padding(start = 22.dp, top = 18.dp)
                .requiredWidth(width = 353.dp)
                .requiredHeight(height = 27.dp)
        ) {
            Text(
                text = "Most Popular",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .requiredWidth(width = 150.dp)
            )
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(start = 317.dp, top = 6.dp)
                    .requiredWidth(width = 47.dp)
                    .requiredHeight(height = 21.dp)
            ) {
                Text(
                    text = "See all",
                    color = Color.Black.copy(alpha = 0.5f),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .requiredWidth(width = 47.dp)
                        .clickable { navController.navigate(Screen.SellAllProduct.route) }
                )
            }
        }
    }

        item{
            Box(
                modifier = Modifier
                    .padding(start = 55.dp, top = 13.dp)
            ) {
                Column {
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
    }
}



@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun ShoppingHomePagePreview() {
    // Create a mock ViewModel
//    ShowRacket(navController = rememberNavController() )
}
