package com.hotelset.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "hotel")
data class Hotel(

@PrimaryKey(autoGenerate = true)
val id : Int,

@ColumnInfo(name = "name")
val name: String,

@ColumnInfo(name = "address")
val address: String?,

@ColumnInfo(name = "description")
val description: String?,

@ColumnInfo(name = "phonenumber")
val phonenumber: String?,

@ColumnInfo(name = "website")
val website: String?,
@ColumnInfo(name = "email")
val email: String?,

@ColumnInfo(name = "latitude")
val latitude: Double?,

@ColumnInfo(name = "longitude")
val longitude: Double?,

@ColumnInfo(name = "height")
val height: Double?,

@ColumnInfo(name = "stars")
val stars: Int?,

@ColumnInfo(name = "rating")
val rating: Double?,

@ColumnInfo(name = "image")
val images: String?,


) : Parcelable
