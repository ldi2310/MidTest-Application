package com.example.midtest.problem1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun CustomerScreen(viewModel: CustomerViewModel) {
    val customers by viewModel.allCustomers.observeAsState(emptyList())

    var showDialog by remember { mutableStateOf(false) }
    var selectedCustomer by remember { mutableStateOf<Customer?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { showDialog = true }) {
            Text("Thêm khách hàng")
        }

        LazyColumn {
            items(customers) { customer ->
                CustomerItem(
                    customer = customer,
                    onEdit = {
                        selectedCustomer = customer
                        showDialog = true
                    },
                    onDelete = { viewModel.delete(customer) }
                )
            }
        }
    }

    if (showDialog) {
        CustomerDialog(
            customer = selectedCustomer,
            onDismiss = { showDialog = false },
            onSave = { customer ->
                if (customer.id == 0) viewModel.insert(customer)
                else viewModel.update(customer)
                showDialog = false
            }
        )
    }
}
