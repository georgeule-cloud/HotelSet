package com.hotelset.repository

import androidx.lifecycle.LiveData
import com.hotelset.data.NoticiaDao
import com.hotelset.model.Hotel
import com.hotelset.model.Noticia

class NoticiaRepository (private val noticiaDao: NoticiaDao){
    val getAllNoticias: LiveData<List<Noticia>> = noticiaDao.getAllNoticias()

    suspend fun addNoticia(noticia: Noticia) {
        noticiaDao.addNoticia(noticia)
    }
    suspend fun deleteNoticia(noticia: Noticia) {
        noticiaDao.deleteNoticia(noticia)
    }
}