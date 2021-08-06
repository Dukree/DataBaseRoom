package com.max.roomdatabase.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.parcelize.Parcelize

@Parcelize
@Entity (tableName = "patient_table")
data class Patient(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName:String,
    val lastName:String,
    val age: Int,
    val diagnosis:String
):Parcelable



