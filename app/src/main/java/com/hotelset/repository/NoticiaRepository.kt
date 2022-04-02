package com.hotelset.repository

import androidx.lifecycle.MutableLiveData
import com.hotelset.data.NoticiaDao
import com.hotelset.model.Noticia

class NoticiaRepository (private val noticiaDao: NoticiaDao){
    val getAllNoticias: MutableLiveData<List<Noticia>> = noticiaDao.getNoticias()

    suspend fun addNoticia(noticia: Noticia) {
        noticiaDao.saveNoticia(noticia)
    }
    suspend fun deleteNoticia(noticia: Noticia) {
        noticiaDao.deleteNoticia(noticia)
    }
}