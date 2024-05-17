package com.example.aceshop

import android.content.Context
import android.net.Uri
import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.aceapps.ui.addRacketProducts
import com.example.aceshop.data.Racket
import com.example.aceshop.data.Shuttlecock
import com.example.aceshop.repo.CartFirebase
import com.example.aceshop.ui.theme.Screen
import com.example.aceshop.viewmodel.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@Composable
fun ItemDetailsRacket(modifier: Modifier = Modifier,
                      navController: NavController,
                      racketViewModel: ViewModel,
                      cartFirebase: CartFirebase,
                      )
{
    val selectedracket =racketViewModel.selectedRacketId
    val racketState = remember { mutableStateOf<Racket?>(null) }
    val db = Firebase.firestore




    LaunchedEffect(selectedracket) {
        racketViewModel.readSingleRacket(db, selectedracket.toString()) { racket ->
            racketState.value = racket
        }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(16.dp) // Add padding to all sides for space between content and screen edges
    ) {
        Column {
            // Image section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp), // Add top padding between image and buttons
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Back button
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
                        .clickable { navController.popBackStack() } // Navigate back on click
                )

                // Cart button
                Image(
                    painter = painterResource(id = R.drawable.cart),
                    contentDescription = "Cart",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(shape = RoundedCornerShape(10.dp))
                        .border(
                            border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { navController.navigate(Screen.Cart.route) }
                )
            }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .padding(bottom = 5.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                ) {
                    racketState.value?.let { racket ->
                        Image(

                            painter = rememberAsyncImagePainter(model = Uri.parse(racket.racket_image)),
                            contentDescription = "Product",
                            modifier = Modifier.fillMaxSize()

                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp) // Add vertical padding between buttons and details
                ) {
                    racketState.value?.let { racket ->
                        Text(
                            text = racket.racket_details,
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp)) // Add spacing between text elements

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Stock text
                            Text(
                                text = "stock: ${racket.racket_stock}",
                                color = Color(0xffa6a6a6).copy(alpha = 0.7f),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )

                            // Price text
                            Text(
                                text = "RM ${racket.racket_price}",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Buy Now button
                        Row(
                            modifier = Modifier
                                .padding(top = 350.dp)
                        ) {
                            // Image
                            Image(
                                painter = painterResource(id = R.drawable.baseline_add_shopping_cart_24),
                                contentDescription = "Cart",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(shape = RoundedCornerShape(10.dp))
                                    .border(
                                        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.5f)),
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .padding(10.dp)
                                    .clickable {
                                        cartFirebase.addCartFirebase(
                                            racket_id = racket.racket_id,
                                            racket_details = racket.racket_details,
                                            racket_image = racket.racket_image,
                                            racket_stock = racket.racket_stock,
                                            racket_price = racket.racket_price,
                                            racket_quantity = 1,
                                        )

                                     }
                            )

                            // Spacer for spacing between image and button
                            Spacer(modifier = Modifier.width(16.dp))

                            // Button
                            Button(
                                onClick = { navController.navigate(Screen.Order.route) },
                                modifier = Modifier
                                    .weight(1f) // Occupy remaining space
                                    .height(54.dp),
                                colors = ButtonDefaults.buttonColors(Color(0xff4a8fce))
                            ) {
                                Text(
                                    text = "Buy Now",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
        }
    }
}







