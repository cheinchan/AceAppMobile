package com.example.aceshop.repo

import android.util.Log
import com.example.aceshop.data.Racket
import com.example.aceshop.data.Shuttlecock
import com.google.firebase.firestore.FirebaseFirestore

class RepoTestRead {
    fun readRacketProduct(db: FirebaseFirestore, callback :(List<Racket>) -> Unit){
        db.collection("racket")
            .get()
            .addOnSuccessListener { documents ->
                val rackets = mutableListOf<Racket>()
                for (document in documents) {
                    try {
                        val racket: Racket = document.toObject(Racket::class.java)
                        rackets.add(racket)
                    }catch (e: Exception){
                        Log.e("Firestore", "Errors")
                    }
                }
                callback(rackets)
            }
            .addOnFailureListener{
                    e -> Log.e("Firestore", "Errors")
            }
    }
    fun readShuttlecockProduct(db: FirebaseFirestore, callback :(List<Shuttlecock>) -> Unit){
        db.collection("shuttlecock")
            .get()
            .addOnSuccessListener { documents ->
                val shuttlecocks = mutableListOf<Shuttlecock>()
                for (document in documents) {
                    try {
                        val shuttlecock: Shuttlecock = document.toObject(Shuttlecock::class.java)
                        shuttlecocks.add(shuttlecock)
                    }catch (e: Exception){
                        Log.e("Firestore", "Errors")
                    }
                }
                callback(shuttlecocks)
            }
            .addOnFailureListener{
                    e -> Log.e("Firestore", "Errors")
            }
    }

    fun readSingleRacketFromFirestore(
        db: FirebaseFirestore,
        racketId: String,
        callback: (Racket?) -> Unit
    ) {
        db.collection("racket")
            .document(racketId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    try {
                        val racket: Racket? = documentSnapshot.toObject(Racket::class.java)
                        callback(racket)
                    } catch (e: Exception) {
                        Log.e("Firestore", "Error converting document to Trip: ${e.message}")
                        callback(null)
                    }
                } else {
                    Log.e("Firestore", "Document does not exist for tripId: $racketId")
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error getting document: ${e.message}", e)
                callback(null)
            }
    }

    fun readSingleShuttlecockFromFirestore(
        db: FirebaseFirestore,
        shuttlecockId: String,
        callback: (Shuttlecock?) -> Unit
    ) {
        db.collection("shuttlecock")
            .document(shuttlecockId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    try {
                        val shuttlecock: Shuttlecock? = documentSnapshot.toObject(Shuttlecock::class.java)
                        callback(shuttlecock)
                    } catch (e: Exception) {
                        Log.e("Firestore", "Error converting document to Trip: ${e.message}")
                        callback(null)
                    }
                } else {
                    Log.e("Firestore", "Document does not exist for tripId: $shuttlecockId")
                    callback(null)
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error getting document: ${e.message}", e)
                callback(null)
            }
    }

}