package com.example.midtest.problem1

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application) : AndroidViewModel(application) {
    private val database = CustomerDatabase.getDatabase(application)
    private var customerDao = database.customerDao()
    val allCustomers: LiveData<List<Customer>>

    init {
        val db = CustomerDatabase.getDatabase(application)
        customerDao = db.customerDao() // Lỗi xảy ra ở đây
        allCustomers = customerDao.getAllCustomers().asLiveData()
    }

    fun insert(customer: Customer) = viewModelScope.launch {
        customerDao.insert(customer)
    }

    fun update(customer: Customer) = viewModelScope.launch {
        customerDao.update(customer)
    }

    fun delete(customer: Customer) = viewModelScope.launch {
        customerDao.delete(customer)
    }
}
