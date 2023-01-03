package com.example.androidtestfindname.data.room

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room

@Database(entities = [Prediction::class], version = 1)
abstract class NameDatabase : RoomDatabase() {
    abstract fun nameDao(): NameDao?

    companion object{
        private var sInstance: NameDatabase? = null

        fun getInstance(context: Context): NameDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, NameDatabase::class.java, "database")
                    .build()
            }
            return sInstance!!
        }
    }
}