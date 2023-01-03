package com.vk.limextask.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vk.limextask.data.entity.ItemFavoriteDbModel

@Dao
interface FavoriteListDao {

    @Query("SELECT * FROM favoriteChannels")
    fun getFavoriteChannelsListLiveData() : List<ItemFavoriteDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addChannel(itemFavoriteDbModel: ItemFavoriteDbModel)

    @Query("DELETE FROM favoriteChannels WHERE itemId=:itemId")
    suspend fun deleteChannel(itemId : Int)

    @Query("SELECT * FROM favoriteChannels WHERE itemId=:itemId LIMIT 1")
    suspend fun getChannel(itemId: Int) : ItemFavoriteDbModel
}