package com.example.aceshop.repo

import com.example.aceshop.data.Racket
import com.example.aceshop.data.Shuttlecock
import com.google.firebase.firestore.FirebaseFirestore

class RepoTestStore {

    // Function to add Racket products to Firebase
    fun addRacketProducts() {
        val db = FirebaseFirestore.getInstance()

        val racket1 = Racket(
            "r1",
            "https://firebasestorage.googleapis.com/v0/b/aceapps-28ab8.appspot.com/o/racket01.jpg?alt=media&token=345f8cbe-8794-4523-80ad-9474b214f229",
            "Yonex Bla Bla",
            20.00,
            30
        )

        val racket2 = Racket(
            "r2",
            "https://firebasestorage.googleapis.com/v0/b/aceapps-28ab8.appspot.com/o/racket01.jpg?alt=media&token=345f8cbe-8794-4523-80ad-9474b214f229",
            "Yonex Bla Bla",
            20.00,
            30
        )

        val racketList = listOf(racket1, racket2)

        for (racket in racketList) {
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

    // Function to add Shuttlecock products to Firebase
    fun addShuttlecockProducts() {
        val db = FirebaseFirestore.getInstance()

        val shuttlecock1 = Shuttlecock(
            "s1",
            "https://firebasestorage.googleapis.com/v0/b/aceapps-28ab8.appspot.com/o/shuttlecock.webp?alt=media&token=69c8c191-986c-4482-9405-2ccccf280d6f",
            "Yonex Bla Bla",
            20.00,
            30
        )

        val shuttlecock2 = Shuttlecock(
            "s2",
            "https://firebasestorage.googleapis.com/v0/b/aceapps-28ab8.appspot.com/o/shuttlecock.webp?alt=media&token=69c8c191-986c-4482-9405-2ccccf280d6f",
            "Yonex a momia",
            50.00,
            99
        )

        val shuttlecockList = listOf(shuttlecock1, shuttlecock2)

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
}