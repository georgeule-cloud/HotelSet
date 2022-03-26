package com.hotelset.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "noticia")
data class Noticia(

@PrimaryKey(autoGenerate = true)
val id : Int,

@ColumnInfo(name = "titulo")
val name: String,

@ColumnInfo(name = "image")
val image: String?,


) : Parcelable
