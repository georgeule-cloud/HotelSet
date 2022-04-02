package com.hotelset.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Noticia(

var id : String,

val name: String,

val body: String,

val image: String?,


) : Parcelable{
    constructor():
            this("","","","")
}
