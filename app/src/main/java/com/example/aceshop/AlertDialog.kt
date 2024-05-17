package com.example.aceshop.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.navigation.NavController

@Composable
fun AlertDialog(
    onDismiss: () -> Unit,
    navController: NavController
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Categories") },
        confirmButton = {
            Button(
                onClick = {  navController.navigate(Screen.Racket.route)}
            ) {
                Text("Racket")
            }
        },
        dismissButton = {
            Button(
                onClick = { navController.navigate(Screen.Shuttlecock.route) }
            ) {
                Text("Shuttlecock")
            }
        },
        modifier = Modifier.height(250.dp)
    )
}
@Composable
fun PaymentConfirmation(
    onDismiss: () -> Unit,
    navController: NavController
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Payment") },
        text = { Text(text = "Your payment was successfully.")},
        confirmButton = {
            Button(
                onClick = {navController.navigate(Screen.ShoppingHomePage.route)},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Done")
            }
        },
        modifier = Modifier.height(250.dp)
    )
}




