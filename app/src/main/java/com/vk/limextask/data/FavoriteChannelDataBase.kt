package com.vk.limextask.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vk.limextask.data.entity.ItemFavoriteDbModel

@Database(entities = [ItemFavoriteDbModel::class], version = 1, exportSchema = false)
abstract class FavoriteChannelDataBase  : RoomDatabase(){

    abstract fun listDao(): FavoriteListDao

    companion object {
        private var INSTANCE : FavoriteChannelDataBase? = null
        private val LOCK = Any()
        private const val DB_NAME = "favoriteChannels.db"

        fun getInstance(application: Application) : FavoriteChannelDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    FavoriteChannelDataBase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}