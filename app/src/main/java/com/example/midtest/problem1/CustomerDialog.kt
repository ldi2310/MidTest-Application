package com.example.midtest.problem1

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerDialog(
    customer: Customer?,
    onDismiss: () -> Unit,
    onSave: (Customer) -> Unit
) {
    var name by remember { mutableStateOf(customer?.name ?: "") }
    var email by remember { mutableStateOf(customer?.email ?: "") }
    var phone by remember { mutableStateOf(customer?.phoneNumber ?: "") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var isValidated by remember { mutableStateOf(false) }  // To trigger validation on submit

    // Function to handle validation
    fun validate() {
        emailError = if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            null
        } else {
            "Địa chỉ email không hợp lệ"
        }

        phoneError = if (phone.matches(Regex("^\\+?\\d{10,13}\$"))) {
            null
        } else {
            "Số điện thoại không hợp lệ"
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nhập thông tin khách hàng") },
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White),
        containerColor = Color.White,
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Tên") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White // Set background to white
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(16.dp))  // Đổ bóng và bo tròn TextField
                        .background(Color.White),  // Đặt nền trắng cho TextField
                    shape = RoundedCornerShape(16.dp)  // Bo tròn TextField
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    isError = emailError != null && isValidated,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White // Set background to white
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(16.dp))  // Đổ bóng và bo tròn TextField
                        .background(Color.White),  // Đặt nền trắng cho TextField
                    shape = RoundedCornerShape(16.dp)  // Bo tròn TextField
                )
                if (emailError != null && isValidated) {
                    Text(
                        text = emailError!!,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("SĐT") },
                    isError = phoneError != null && isValidated,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White // Set background to white
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .shadow(4.dp, shape = RoundedCornerShape(16.dp))  // Đổ bóng và bo tròn TextField
                        .background(Color.White),  // Đặt nền trắng cho TextField
                    shape = RoundedCornerShape(8.dp)  // Bo tròn TextField
                )
                if (phoneError != null && isValidated) {
                    Text(
                        text = phoneError!!,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    validate()
                    isValidated = true  // Trigger validation on submit
                    if (emailError == null && phoneError == null) {
                        onSave(Customer(customer?.id ?: 0, name, email, phone))
                    }
                },
                enabled = emailError == null && phoneError == null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Brush.linearGradient(
                        colors = listOf(Color(0xFF64B5F6), Color(0xFF1E88E5)))),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)  // Màu nền trong suốt để hiển thị gradient
            ) {
                Text("Lưu", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Brush.linearGradient(
                        colors = listOf(Color(0xFFE53935), Color(0xFFD32F2F)))),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Màu nền trong suốt để hiển thị gradient
            ) {
                Text("Hủy", color = Color.White)
            }
        }
    )

}
