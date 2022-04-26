package com.hotelset.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.hotelset.model.Simbolo


class SimboloDao {

    private val coleccion1 = "simbolos"
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init{
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //Funcion para obtener la lista de simbolos
    fun getSimbolos(): MutableLiveData<List<Simbolo>> {
        val listaSimbolos = MutableLiveData<List<Simbolo>>()

        firestore.collection(coleccion1)
            .addSnapshotListener{ instantanea, e ->
                if (e != null){
                    return@addSnapshotListener
                }

                if(instantanea != null){
                    val lista = ArrayList<Simbolo>()

                    instantanea.documents.forEach{
                        val simbolo = it.toObject(Simbolo::class.java)
                        if (simbolo!= null){
                            lista.add(simbolo)
                        }
                    }
                    listaSimbolos.value = lista
                }
            }

        return listaSimbolos

    }
}