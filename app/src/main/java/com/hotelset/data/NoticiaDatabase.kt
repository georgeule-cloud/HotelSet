package com.hotelset.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hotelset.model.Noticia


@Database(entities = [Noticia::class], version = 1, exportSchema = false)

abstract class NoticiaDatabase : RoomDatabase() {
    abstract fun noticiaDao(): NoticiaDao

    companion object{
        @Volatile
        private var INSTANCE: NoticiaDatabase? = null

        fun getDatabase(context: android.content.Context) : NoticiaDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoticiaDatabase::class.java,
                    "noticia_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}