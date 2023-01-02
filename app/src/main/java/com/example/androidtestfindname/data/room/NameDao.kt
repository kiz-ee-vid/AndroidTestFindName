package com.example.androidtestfindname.data.room

import androidx.room.*

@Dao
interface NameDao {
    @Query("SELECT * FROM name")
    fun getAll(): MutableList<Name>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(name: Name)

    @Delete
    fun delete(nameList: ArrayList<Name>)

    @Query("DELETE FROM name")
    fun clearTable()
}