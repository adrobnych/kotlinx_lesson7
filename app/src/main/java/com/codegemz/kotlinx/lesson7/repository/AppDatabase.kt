package com.codegemz.kotlinx.lesson7.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codegemz.kotlinx.lesson7.repository.entity.SearchEntity

@Database(entities = [SearchEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}