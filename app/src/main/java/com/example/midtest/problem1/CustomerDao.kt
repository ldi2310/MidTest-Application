package com.example.midtest.problem1

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert
    suspend fun insert(customer: Customer)

    @Update
    suspend fun update(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)

    @Query("SELECT * FROM Customer")
    fun getAllCustomers(): Flow<List<Customer>>

}

