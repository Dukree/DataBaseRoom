package com.max.roomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Patient::class], version = 1, exportSchema = false)
abstract class PatientDatabase:RoomDatabase() {

  abstract  fun patientDao(): PatientDao

  companion object{
      @Volatile
      private var INSTANCE: PatientDatabase? =null

      fun getDatabase(context :Context) : PatientDatabase{
          val tempInstance = INSTANCE
          if (tempInstance != null){
              return tempInstance
          }
          synchronized(this){
              val instance =Room.databaseBuilder(
                  context.applicationContext,
                  PatientDatabase::class.java,
                  "patient_database"
              ).build()
              INSTANCE = instance
              return instance
          }
      }
  }
}