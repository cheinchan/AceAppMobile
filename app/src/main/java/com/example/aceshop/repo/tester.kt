package com.example.aceapps.ui


import android.util.Log
import com.example.aceshop.data.Racket
import com.example.aceshop.data.Shuttlecock
import com.google.firebase.firestore.FirebaseFirestore

data class Racket(
    val racket_id: String,
    val racket_image: String,
    val racket_name: String,
    val racket_price: Double,
    val racket_stock: Int
)

data class Shuttlecock(
    val shuttlecock_id: String,
    val shuttlecock_image: String,
    val shuttlecock_name: String,
    val shuttlecock_price: Double,
    val shuttlecock_stock: Int
)

// Function to add Racket products to Firebase
fun addRacketProducts() {
    val db = FirebaseFirestore.getInstance()
    val racketList = mutableListOf<Racket>()

    for (i in 1..10) {
        val racket = Racket(
            "r$i",
            "https://firebasestorage.googleapis.com/v0/b/aceapps-28ab8.appspot.com/o/racket01.jpg?alt=media&token=345f8cbe-8794-4523-80ad-9474b214f229",
            when (i) {
                1 -> "ASTROX 88 PLAY 3RD GENERATION"
                2 -> "AXFORCE CANNON LIGHT"
                3 -> "NANORAY 72 LIGHT"
                4 -> "ASTROX NEXTAGE"
                5 -> "NANORAY 72 LIGHT BLUE"
                6 -> "ARCSABER 73 LIGHT TURQUOISE"
                7 -> "ATOMIC X BLACK KNIGHT"
                8 -> "ATOMIC X DARK SWORD"
                9 -> "N-ERGY 80"
                10 -> "HUNDRED N-ERGY 80"
                else -> ""
            },
            when (i) {
                in 1..5 -> 309.0
                else -> 157.0
            },
            when (i) {
                in 1..5 -> 150
                else -> 110
            }
        )
        racketList.add(racket)
    }

    racketList.forEach { racket ->
        db.collection("racket")
            .document(racket.racket_id)
            .set(racket)
            .addOnSuccessListener {
                println("Racket product added to Firestore with ID: ${racket.racket_id}")
            }
            .addOnFailureListener { e ->
                println("Error adding Racket product to Firestore: $e")
            }
    }
}

fun addShuttlecockProducts() {
    val db = FirebaseFirestore.getInstance()
    val shuttlecockList = mutableListOf<Shuttlecock>()

    // Create and add data for shuttlecock products
    for (i in 1..10) {
        val shuttlecock = Shuttlecock(
            "s$i",
            "https://firebasestorage.googleapis.com/v0/b/aceapps-28ab8.appspot.com/o/shuttlecock.webp?alt=media&token=69c8c191-986c-4482-9405-2ccccf280d6f",
            when (i) {
                1 -> "YONEX SHUTTLECOCK AEROSENSA 50"
                2 -> "YONEX SHUTTLECOCK AEROSENSA 40"
                3 -> "RSL Classic Tourney Shuttlecock"
                4 -> "RSL Supreme Shuttlecock"
                5 -> "RSL Ultimate Shuttlecock"
                6 -> "RSL Gold No. 1 Tourney Shuttlecock"
                7 -> "PROTECH Platinum Shuttlecock"
                8 -> "RSL A9 Shuttlecock"
                9 -> "RSL DTL 81 Shuttlecock"
                10 -> "MAXBOLT GREEN SHUTTLECOCK SP77"
                else -> ""
            },
            when (i) {
                1 -> 139.00
                2 -> 120.00
                3 -> 29.00
                4 -> 49.00
                5 -> 24.00
                6 -> 20.00
                7 -> 24.00
                8 -> 19.00
                9 -> 51.00
                10 -> 65.00
                else -> 0.00
            },
            when (i) {
                1 -> 120
                2 -> 190
                3 -> 122
                4 -> 220
                5 -> 320
                6 -> 120
                7 -> 140
                8 -> 90
                9 -> 70
                10 -> 133
                else -> 0
            }
        )
        shuttlecockList.add(shuttlecock)
    }

    // Add shuttlecock products to Firestore
    for (shuttlecock in shuttlecockList) {
        db.collection("shuttlecock")
            .document(shuttlecock.shuttlecock_id)
            .set(shuttlecock)
            .addOnSuccessListener {
                println("Shuttlecock product added to Firestore with ID: ${shuttlecock.shuttlecock_id}")
            }
            .addOnFailureListener { e ->
                println("Error adding Shuttlecock product to Firestore: $e")
            }
    }
}



fun main() {
    addRacketProducts()
    addShuttlecockProducts()
}







