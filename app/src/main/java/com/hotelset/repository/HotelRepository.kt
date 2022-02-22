package com.hotelset.repository

import androidx.lifecycle.LiveData
import com.hotelset.data.HotelDao
import com.hotelset.model.Hotel

class HotelRepository (private val hotelDao: HotelDao){
    val getAllHotels: LiveData<List<Hotel>> = hotelDao.getAllHotels()

    suspend fun addHotel(hotel: Hotel) {
        hotelDao.addHotel(hotel)
    }
    suspend fun updateHotel(hotel: Hotel) {
        hotelDao.updateHotel(hotel)
    }
    suspend fun deleteHotel(hotel: Hotel) {
        hotelDao.deleteHotel(hotel)
    }
}