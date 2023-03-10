package com.example.androidtestfindname.data.room

import androidx.room.*

@Dao
interface NameDao {
    @Query("SELECT * FROM prediction")
    fun getAll(): MutableList<Prediction>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(prediction: Prediction)

    @Delete
    fun delete(predictionList: ArrayList<Prediction>)

    @Query("DELETE FROM prediction")
    fun clearTable()
}