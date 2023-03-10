package com.vk.limextask.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteChannels")
data class ItemFavoriteDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "itemId")
    val itemId : Int
)
