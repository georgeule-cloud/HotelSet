package com.hotelset.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hotelset.model.Hotel

@Database(entities = [Hotel::class], version = 1, exportSchema = false)

abstract class HotelDatabase : RoomDatabase() {
    abstract fun hotelDao(): HotelDao

    companion object{
        @Volatile
        private var INSTANCE: HotelDatabase? = null

        fun getDatabase(context: android.content.Context) : HotelDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HotelDatabase::class.java,
                    "hotel_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}