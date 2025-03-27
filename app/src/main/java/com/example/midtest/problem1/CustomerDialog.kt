package com.example.midtest.problem1

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun CustomerDialog(
    customer: Customer?,
    onDismiss: () -> Unit,
    onSave: (Customer) -> Unit
) {
    var name by remember { mutableStateOf(customer?.name ?: "") }
    var email by remember { mutableStateOf(customer?.email ?: "") }
    var phone by remember { mutableStateOf(customer?.phoneNumber ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nhập thông tin khách hàng") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Tên") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("SĐT") })
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(Customer(customer?.id ?: 0, name, email, phone))
            }) {
                Text("Lưu")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}
