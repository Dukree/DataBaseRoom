package com.max.roomdatabase.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(application: Application):AndroidViewModel(application) {

     val readAllData: LiveData<List<Patient>>
    private val repository: PatientRepository

    init {
        val patientDao = PatientDatabase.getDatabase(application).patientDao()
        repository = PatientRepository(patientDao)
        readAllData =repository.readAllData
    }

    fun addPatient(patient:Patient){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPatient(patient)
        }
    }

    fun deletePatient(patient: Patient){
        viewModelScope.launch (Dispatchers.IO){
            repository.deletePatient(patient)
        }
    }

     fun deleteAllPatients(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllPatients()
        }

    }
}