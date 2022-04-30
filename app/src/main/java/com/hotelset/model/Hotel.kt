package com.hotelset.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hotel(

    var id : String,

    val name: String,

    val address: String?,

    val description: String?,

    val phonenumber: String?,

    val website: String?,

    val email: String?,

    val latitude: Double?,

    val longitude: Double?,

    val height: Double?,

    val stars: Int?,

    val rating: Double?,

    val rutaAudio: String?,

    val rutaImagen: String?,
    val tipo: String?


    ) : Parcelable{
    constructor() :
            this("","","","","","","",0.0,0.0,0.0,0,0.0,"","","")
}
