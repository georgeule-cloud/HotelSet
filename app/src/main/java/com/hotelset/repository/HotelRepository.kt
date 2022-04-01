package com.hotelset.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hotelset.data.HotelDao
import com.hotelset.model.Hotel

class HotelRepository (private val hotelDao: HotelDao){
    val getAllHotels: MutableLiveData<List<Hotel>> = hotelDao.getHotels()

    suspend fun addHotel(hotel: Hotel) {
        hotelDao.saveHotel(hotel)
    }
    suspend fun updateHotel(hotel: Hotel) {
        hotelDao.saveHotel(hotel)
    }
    suspend fun deleteHotel(hotel: Hotel) {
        hotelDao.deleteHotel(hotel)
    }
}