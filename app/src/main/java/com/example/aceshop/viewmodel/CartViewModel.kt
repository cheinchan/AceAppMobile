package com.example.aceshop.viewmodel

import android.content.Context
import com.example.aceshop.repo.CartFirebase
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class CartViewModel {
    private val database = CartFirebase()
    fun addTrip(context: Context, db: FirebaseFirestore, racket_id: String, racket_details: String, racket_image:String, racket_stock:Int, racket_price:Double, racket_quantity:Int ){
        val db =Firebase.firestore
        database.addCartFirebase(

            racket_id = racket_id,
            racket_details=  racket_details,
            racket_image = racket_image,
            racket_stock = racket_stock,
            racket_price = racket_price,
            racket_quantity = racket_quantity,
        )
    }
}