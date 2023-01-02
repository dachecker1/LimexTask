package com.vk.limextask.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vk.limextask.data.entity.ItemFavoriteDbModel

@Dao
interface FavoriteListDao {

    @Query("SELECT * FROM favoriteChannels")
    suspend fun getFavoriteList() : List<ItemFavoriteDbModel>

    @Query("SELECT * FROM favoriteChannels")
    fun getFavoriteChannelsListLiveData() : LiveData<List<ItemFavoriteDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChannel(itemFavoriteDbModel: ItemFavoriteDbModel)

    @Query("DELETE FROM favoriteChannels WHERE id=:itemId")
    suspend fun deleteChannel(itemId : Int)

    @Query("SELECT * FROM favoriteChannels WHERE id=:itemId LIMIT 1")
    suspend fun getChannel(itemId: Int) : ItemFavoriteDbModel
}