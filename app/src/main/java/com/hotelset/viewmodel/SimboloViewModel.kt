package com.hotelset.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hotelset.data.SimboloDao
import com.hotelset.model.Simbolo
import com.hotelset.repository.SimboloRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SimboloViewModel(application: Application) : AndroidViewModel(application) {
    val getAllSymbols: MutableLiveData<List<Simbolo>>

    private val repository : SimboloRepository = SimboloRepository(SimboloDao())

    init {
        getAllSymbols = repository.getAllSimbolos
    }
    

}
