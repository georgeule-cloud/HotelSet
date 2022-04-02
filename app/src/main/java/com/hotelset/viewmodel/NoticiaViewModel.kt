package com.hotelset.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hotelset.data.NoticiaDao
import com.hotelset.model.Noticia
import com.hotelset.repository.NoticiaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoticiaViewModel (application: Application) : AndroidViewModel(application) {
    val getAllNoticias: MutableLiveData<List<Noticia>>

    private val repository : NoticiaRepository = NoticiaRepository(NoticiaDao())

    init {
        getAllNoticias = repository.getAllNoticias
    }
    //
    fun addNoticia(noticia: Noticia) {
        viewModelScope.launch(Dispatchers.IO) {repository.addNoticia(noticia)}
    }
    fun deleteHotel(noticia: Noticia) {
        viewModelScope.launch(Dispatchers.IO) {repository.deleteNoticia(noticia)}
    }

}