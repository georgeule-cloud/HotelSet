package com.hotelset.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.hotelset.data.NoticiaDao
import com.hotelset.data.NoticiaDatabase
import com.hotelset.model.Noticia
import com.hotelset.repository.NoticiaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoticiasViewModel (application: Application) : AndroidViewModel(application) {
    val getAllNoticias: LiveData<List<Noticia>>

    private val repository : NoticiaRepository

    init {
        val noticiaDao = NoticiaDatabase.getDatabase(application).noticiaDao()
        repository = NoticiaRepository(noticiaDao)
        getAllNoticias = repository.getAllNoticias
    }

    fun addNoticia(noticia: Noticia) {
        viewModelScope.launch(Dispatchers.IO) {repository.addNoticia(noticia)}
    }
    fun deleteHotel(noticia: Noticia) {
        viewModelScope.launch(Dispatchers.IO) {repository.deleteNoticia(noticia)}
    }

}