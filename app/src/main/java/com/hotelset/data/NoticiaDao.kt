package com.hotelset.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hotelset.model.Noticia

@Dao
interface NoticiaDao {
    //Funcion para obtener la lista de noticias
    @Query("select * from noticia")
    fun getAllNoticias(): LiveData<List<Noticia>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNoticia(noticia: Noticia)

    @Delete
    suspend fun deleteNoticia(noticia: Noticia)
}