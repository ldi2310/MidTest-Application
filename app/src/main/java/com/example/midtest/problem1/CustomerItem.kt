package com.example.midtest.problem1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment

@Composable
fun CustomerItem(customer: Customer, onEdit: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Tên: ${customer.name}", fontWeight = FontWeight.Bold)
                Text(text = "Email: ${customer.email}")
                Text(text = "SĐT: ${customer.phoneNumber}")
            }

            // Căn nút chỉnh sửa và xóa về góc phải
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Sửa")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Xóa", tint = Color.Red) // Temporary fix with red
                }
            }
        }
    }
}
