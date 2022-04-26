package com.hotelset.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hotelset.data.HotelDao
import com.hotelset.data.SimboloDao
import com.hotelset.model.Hotel
import com.hotelset.model.Simbolo

class SimboloRepository (private val simboloDao: SimboloDao){
    val getAllSimbolos: MutableLiveData<List<Simbolo>> = simboloDao.getSimbolos()
}