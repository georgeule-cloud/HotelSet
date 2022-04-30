package com.hotelset.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hotelset.data.HotelDao
import com.hotelset.model.Hotel

class HotelRepository (private val hotelDao: HotelDao){
    var getAllHotels: MutableLiveData<List<Hotel>> = hotelDao.getHotels()
    var getPlayaHotels: MutableLiveData<List<Hotel>> = hotelDao.getPlayaHotels()
    var getMontanaHotels: MutableLiveData<List<Hotel>> = hotelDao.getMontanaHotels()
    var getCiudadHotels: MutableLiveData<List<Hotel>> = hotelDao.getCiudadHotels()





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