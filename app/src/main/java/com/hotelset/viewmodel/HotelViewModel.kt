package com.hotelset.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hotelset.data.HotelDao
import com.hotelset.model.Hotel
import com.hotelset.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelViewModel (application: Application) : AndroidViewModel(application) {
    val getAllHotels: MutableLiveData<List<Hotel>>

    private val repository : HotelRepository = HotelRepository(HotelDao())

    init {
        getAllHotels = repository.getAllHotels
    }

    fun addHotel(hotel: Hotel) {
        viewModelScope.launch(Dispatchers.IO) {repository.addHotel(hotel)}
    }
    fun updateHotel(hotel: Hotel) {
        viewModelScope.launch(Dispatchers.IO) {repository.updateHotel(hotel)}
    }
    fun deleteHotel(hotel: Hotel) {
        viewModelScope.launch(Dispatchers.IO) {repository.deleteHotel(hotel)}
    }

}
