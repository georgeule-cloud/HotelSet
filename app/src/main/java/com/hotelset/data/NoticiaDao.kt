package com.hotelset.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.hotelset.model.Hotel
import com.hotelset.model.Noticia

class NoticiaDao {

    private val coleccion1 = "hotelAPP"
    private val coleccion2 = "misNoticias"
    private val usuario = Firebase.auth.currentUser?.email.toString()
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init{
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //Funcion para obtener la lista de noticias
    fun getNoticias(): MutableLiveData<List<Noticia>> {
        val listaNoticias = MutableLiveData<List<Noticia>>()

        firestore.collection(coleccion1).document(usuario).collection(coleccion2)
            .addSnapshotListener{ instantanea, e ->
                if (e != null){
                    return@addSnapshotListener
                }

                if(instantanea != null){
                    val lista = ArrayList<Noticia>()

                    instantanea.documents.forEach{
                        val noticia = it.toObject(Noticia::class.java)
                        if (noticia!= null){
                            lista.add(noticia)
                        }
                    }
                    listaNoticias.value = lista
                }
            }

        return listaNoticias

    }

    suspend fun saveNoticia(noticia: Noticia){

            val documento: DocumentReference

            if (noticia.id.isEmpty()){
                documento = firestore.collection(coleccion1).document(usuario).collection(coleccion2).document()
                noticia.id = documento.id
            }else{
                documento = firestore.collection(coleccion1).document(usuario).collection(coleccion2).document(noticia.id)
            }

            documento.set(noticia)
                .addOnSuccessListener { Log.d("saveNoticia","Noticia agregada/modificada") }
                .addOnCanceledListener { Log.e("saveNoticia","Error: Noticia NO agregada/modificada") }

        }

    suspend fun deleteNoticia(noticia: Noticia){

        if (noticia.id.isNotEmpty()){
            firestore.collection(coleccion1).document(usuario).collection(coleccion2).document(noticia.id).delete()
                .addOnSuccessListener { Log.d("deleteNoticia","Noticia eliminada") }
                .addOnCanceledListener { Log.e("deleteNoticia","Error: Noticia NO eliminada") }
        }

    }
}