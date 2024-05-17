import android.net.Uri
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.aceshop.R
import com.example.aceshop.data.Racket
import com.example.aceshop.data.Shuttlecock
import com.example.aceshop.ui.theme.Screen
import com.example.aceshop.viewmodel.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


@Composable
fun Cart(modifier: Modifier = Modifier
         ,navController: NavController,
         racketViewModel: ViewModel,
         shuttlecockViewModel: ViewModel)
{
    val racketListState = remember { mutableStateOf<List<Racket>>(emptyList())}
    val shuttlecockListState = remember { mutableStateOf<List<Shuttlecock>>(emptyList())}

        val db = Firebase.firestore
        LaunchedEffect(key1 = true) {
            racketViewModel.readRacket(db) { racket ->
                val racketData = racket.toList()
                racketListState.value = racketData
            }
        }
    LaunchedEffect(key1 = true) {
        shuttlecockViewModel.readShuttlecock(db) { shuttlecock ->
            val shuttlecockData = shuttlecock.toList()
            shuttlecockListState.value = shuttlecockData
        }
    }
    Box(
        modifier = modifier
            .requiredWidth(width = 393.dp)
            .requiredHeight(height = 852.dp)
            .background(color = Color.White)
    ) {
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
                    text = "Cart",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp) // Add padding between the image and text
                )
            }
        }


        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(top=636.dp)
                .requiredWidth(width = 393.dp)
                .requiredHeight(height = 219.dp)
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 393.dp)
                    .requiredHeight(height = 219.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 393.dp)
                        .requiredHeight(height = 219.dp)
                        .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(color = Color.White))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(top = 29.dp, start = 20.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 25.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(start = 328.dp)
                        .requiredSize(size = 25.dp)
                        .clip(shape = RoundedCornerShape(5.dp))
                        .border(
                            border = BorderStroke(1.dp, Color(0xffb9b9b9)),
                            shape = RoundedCornerShape(5.dp)
                        ))
                Text(
                    text = "Select All",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 0.81.em,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(top = 6.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(top = 137.dp, start = 20.dp)
                    .requiredWidth(width = 353.dp)
                    .requiredHeight(height = 53.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screen.Process.route)
                    },
                    modifier = Modifier
                        .requiredWidth(width = 353.dp)
                        .requiredHeight(height = 53.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                            colors = ButtonDefaults.buttonColors(Color(0xff4a8fce))
                ) {
                    Text(
                        text = "Checkout",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 0.87.em,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .padding(top = 93.dp, start = 20.dp)
                    .requiredWidth(width = 360.dp)
                    .requiredHeight(height = 21.dp)
            ) {
                Text(
                    text = "RM 140.00",
                    color = Color.Black,
                    style = TextStyle(
                        fontSize = 14.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(start = 289.dp))
                Text(
                    text = "Total Payment",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 0.81.em,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .padding(top = 4.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically))
            }
        }
    }
}

@Composable
fun PlusMinusIcon() {
    var value by remember { mutableStateOf(0) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        IconButton(onClick = {
            if (value > 0) value--
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_remove_24),
                contentDescription = "Minus"
            )
        }

        Text(text = value.toString())

        IconButton(onClick = { value++ }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Plus"
            )
        }
    }
}

@Composable
fun AddPlusButton(modifier: Modifier = Modifier) {
    var quantity by remember { mutableStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
        ) {
            Button(
                onClick = { if (quantity > 0) {
                    quantity--
                } },
                modifier = Modifier.weight(1f), // Use weight to distribute available space equally
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = "-", color = Color.Black)
            }

            Spacer(modifier = Modifier.width(8.dp)) // Add spacing between buttons and text

            Text(
                text = "$quantity",
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(8.dp)) // Add spacing between buttons and text

            Button(
                onClick = { quantity++ },
                modifier = Modifier.weight(1f), // Use weight to distribute available space equally
                colors = ButtonDefaults.buttonColors(Color.White),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = "+", color = Color.Black)
            }
        }
    }
}


@Preview(widthDp = 393, heightDp = 852)
@Composable
private fun CartPreview() {
//    Cart(Modifier, navController = rememberNavController(), racketViewModel = ViewModel())
}