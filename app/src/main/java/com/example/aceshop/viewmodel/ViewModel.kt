package com.example.aceshop.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aceshop.data.Racket
import com.example.aceshop.data.Shuttlecock
import com.example.aceshop.repo.CartFirebase
import com.example.aceshop.repo.RepoTestRead
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ViewModel: ViewModel() {
    private val database = RepoTestRead()
    var selectedRacketId: String? = null
    var selectedShuttlecockId: String? = null


    fun readRacket(db: FirebaseFirestore, callback: (List<Racket>) -> Unit){
        database.readRacketProduct(db = db,callback = callback)
    }

    fun readShuttlecock(db: FirebaseFirestore, callback: (List<Shuttlecock>) -> Unit){
        database.readShuttlecockProduct(db = db,callback = callback)
    }

    fun readSingleRacket(db: FirebaseFirestore, racketId: String, callback: (Racket?) -> Unit){
        database.readSingleRacketFromFirestore(
            db = db,
            racketId = racketId,
            callback = callback
        )
    }

    fun readSingleShuttlecock(db: FirebaseFirestore, shuttlecockId: String, callback: (Shuttlecock?) -> Unit){
        database.readSingleShuttlecockFromFirestore(
            db = db,
            shuttlecockId = shuttlecockId,
            callback = callback
        )
    }
}
