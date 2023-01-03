package com.example.androidtestfindname.data.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Prediction(
    @PrimaryKey
    var name: String,
    var age: Byte
):Parcelable