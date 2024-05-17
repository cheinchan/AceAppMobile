package com.example.aceshop.repo

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class CartFirebase {
fun addCartFirebase(
    racket_id: String,
    racket_details: String,
    racket_image: String,
    racket_stock: Int,
    racket_price: Double,
    racket_quantity: Int,
) {
    val db = Firebase.firestore

    // Create a map containing the data to be saved
    val cartData = hashMapOf(
        "racket_id" to racket_id,
        "racket_details" to racket_details,
        "racket_image" to racket_image,
        "racket_stock" to racket_stock,
        "racket_price" to racket_price,
        "racket_quantity" to racket_quantity
    )

    // Reference to the document in the "Cart" collection
    val documentReference = db.collection("Cart").document("cart$racket_id")

    // Set the document data
    documentReference
        .set(cartData)
        .addOnSuccessListener {
            // Log a success message
            Log.d("Firestore", "Document added successfully: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            // Log an error message
            Log.e("Firestore", "Error adding document", e)
        }
}

}