package com.example.midtest.problem1

import android.content.Context
import androidx.room.*


@Database(entities = [Customer::class], version = 1, exportSchema = false)
abstract class CustomerDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao

    companion object {
        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getDatabase(context: Context): CustomerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerDatabase::class.java,
                    "customer_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
