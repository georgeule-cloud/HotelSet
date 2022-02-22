package com.hotelset.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hotelset.model.Hotel

@Dao
interface HotelDao {
    //Funcion para obtener la lista de hoteles
    @Query("select * from hotel")
    fun getAllHotels(): LiveData<List<Hotel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHotel(lugar: Hotel)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateHotel(lugar: Hotel)

    @Delete
    suspend fun deleteHotel(lugar: Hotel)
}