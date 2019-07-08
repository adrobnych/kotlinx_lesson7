package com.codegemz.kotlinx.lesson7

import androidx.room.Room
import androidx.multidex.MultiDexApplication
import com.codegemz.kotlinx.lesson7.net.RestService
import com.codegemz.kotlinx.lesson7.repository.AppDatabase
import com.codegemz.kotlinx.lesson7.repository.SearchRepository

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        installProviders()
    }

    private fun installProviders() {
        dataBase = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        lateinit var dataBase: AppDatabase
            private set

        val restService by lazy {
            RestService.create()
        }

        var searchRepository = SearchRepository()
    }
}