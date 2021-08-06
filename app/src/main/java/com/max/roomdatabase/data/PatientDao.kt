package com.max.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PatientDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPatient(patient:Patient)

    @Delete
    suspend fun deletePatient(patient: Patient)

    @Query ("DELETE FROM patient_table")
    suspend fun deleteAllPatients()


    @Query("SELECT * FROM patient_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Patient>>
}