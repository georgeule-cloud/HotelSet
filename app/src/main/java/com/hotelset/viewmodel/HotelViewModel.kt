package com.hotelset.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hotelset.data.HotelDao
import com.hotelset.model.Hotel
import com.hotelset.repository.HotelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelViewModel (application: Application) : AndroidViewModel(application) {
    var getAllHotels: MutableLiveData<List<Hotel>>
    var getPlayaHotels: MutableLiveData<List<Hotel>>
    var getMontanaHotels: MutableLiveData<List<Hotel>>
    var getCiudadHotels: MutableLiveData<List<Hotel>>
    private var repository : HotelRepository = HotelRepository(HotelDao())

    init {
        getAllHotels = repository.getAllHotels
        getPlayaHotels = repository.getPlayaHotels
        getMontanaHotels = repository.getMontanaHotels
        getCiudadHotels = repository.getCiudadHotels
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
