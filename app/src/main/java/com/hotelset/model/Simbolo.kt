package com.hotelset.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Simbolo(

var id : String,


val title: String,

val image: String?,


) : Parcelable{
    constructor():
            this("","","")
}
