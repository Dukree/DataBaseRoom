package com.max.roomdatabase.data

import androidx.lifecycle.LiveData

class PatientRepository(private val patientDao:PatientDao) {
    val readAllData:LiveData<List<Patient>> = patientDao.readAllData()

    suspend fun addPatient(patient: Patient){
        patientDao.addPatient(patient)
    }

    suspend fun deletePatient(patient: Patient){
        patientDao.deletePatient(patient)
    }

    suspend fun deleteAllPatients() {
        patientDao.deleteAllPatients()
    }


}