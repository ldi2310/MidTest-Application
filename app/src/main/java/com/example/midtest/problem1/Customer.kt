package com.example.midtest.problem1

import androidx.room.*

@Entity(tableName = "Customer")
data class Customer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val phoneNumber: String
)
