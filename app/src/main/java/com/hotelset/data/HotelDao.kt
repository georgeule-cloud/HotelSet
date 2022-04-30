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

class HotelDao() {
    private val coleccion1 = "hotelAPP"
    private val coleccion2 = "misHoteles"
    private val usuario = Firebase.auth.currentUser?.email.toString()
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    //Funcion para obtener la lista de hoteles
    fun getHotels(): MutableLiveData<List<Hotel>> {
        val listaHoteles = MutableLiveData<List<Hotel>>()

        firestore.collection(coleccion1).document(usuario).collection(coleccion2)
            .addSnapshotListener{ instantanea, e ->
                if (e != null){
                    return@addSnapshotListener
                }

                if(instantanea != null){
                    val lista = ArrayList<Hotel>()

                    instantanea.documents.forEach{
                        val hotel = it.toObject(Hotel::class.java)
                        if (hotel!= null){

                                    lista.add(hotel)
                            }
                    }
                    listaHoteles.value = lista
                }
            }

        return listaHoteles

    }

    fun getPlayaHotels(): MutableLiveData<List<Hotel>> {
        val listaHoteles = MutableLiveData<List<Hotel>>()

        firestore.collection(coleccion1).document(usuario).collection(coleccion2)
            .addSnapshotListener{ instantanea, e ->
                if (e != null){
                    return@addSnapshotListener
                }

                if(instantanea != null){
                    val lista = ArrayList<Hotel>()

                    instantanea.documents.forEach{
                        val hotel = it.toObject(Hotel::class.java)
                        if (hotel!= null){

                        if(hotel.tipo.equals("Playa")){
                                        lista.add(hotel)
                        }
                            }
                    }
                    listaHoteles.value = lista
                }
            }

        return listaHoteles

    }
    fun getMontanaHotels(): MutableLiveData<List<Hotel>> {
        val listaHoteles = MutableLiveData<List<Hotel>>()

        firestore.collection(coleccion1).document(usuario).collection(coleccion2)
            .addSnapshotListener{ instantanea, e ->
                if (e != null){
                    return@addSnapshotListener
                }

                if(instantanea != null){
                    val lista = ArrayList<Hotel>()

                    instantanea.documents.forEach{
                        val hotel = it.toObject(Hotel::class.java)
                        if (hotel!= null){

                            if(hotel.tipo.equals("Montana")){
                                lista.add(hotel)
                            }
                        }
                    }
                    listaHoteles.value = lista
                }
            }

        return listaHoteles

    }

    fun getCiudadHotels(): MutableLiveData<List<Hotel>> {
        val listaHoteles = MutableLiveData<List<Hotel>>()

        firestore.collection(coleccion1).document(usuario).collection(coleccion2)
            .addSnapshotListener{ instantanea, e ->
                if (e != null){
                    return@addSnapshotListener
                }

                if(instantanea != null){
                    val lista = ArrayList<Hotel>()

                    instantanea.documents.forEach{
                        val hotel = it.toObject(Hotel::class.java)
                        if (hotel!= null){

                            if(hotel.tipo.equals("Ciudad")){
                                lista.add(hotel)
                            }
                        }
                    }
                    listaHoteles.value = lista
                }
            }

        return listaHoteles

    }

    fun saveHotel(hotel: Hotel){

        val documento: DocumentReference

        if (hotel.id.isEmpty()){
            documento = firestore.collection(coleccion1).document(usuario).collection(coleccion2).document()
            hotel.id = documento.id
        }else{
            documento = firestore.collection(coleccion1).document(usuario).collection(coleccion2).document(hotel.id)
        }

        documento.set(hotel)
            .addOnSuccessListener { Log.d("saveHotel","Hotel agregado/modificado") }
            .addOnCanceledListener { Log.e("saveHotel","Error: Hotel NO agregado/modificado") }

    }

    fun deleteHotel(hotel: Hotel){

        if (hotel.id.isNotEmpty()){
            firestore.collection(coleccion1).document(usuario).collection(coleccion2).document(hotel.id).delete()
                .addOnSuccessListener { Log.d("deleteHotel","Hotel eliminado") }
                .addOnCanceledListener { Log.e("deleteHotel","Error: Hotel NO eliminado") }
        }

    }
}