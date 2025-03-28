package com.example.midtest.problem1

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerScreen(viewModel: CustomerViewModel) {
    val customers by viewModel.allCustomers.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var selectedCustomer by remember { mutableStateOf<Customer?>(null) }

    val filteredCustomers = customers.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(
                        Brush.linearGradient(
                            colors = listOf(Color(0xFF2196F3), Color(0xFF64B5F6))
                        )
                    )
            ) {
                TopAppBar(
                    title = { Text("Danh sách khách hàng: ${filteredCustomers.size}", color = Color.White, fontSize = 30.sp) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                )
                // Apply TextField with a white background
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Tìm kiếm khách hàng...") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White // Set background to white
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White, shape = CircleShape) // Apply circular background
                        //.padding(horizontal = 16.dp, vertical = 8.dp) // Padding inside the rounded field
                        .shadow(4.dp, shape = CircleShape) // Add shadow if needed
                )
            }
        },
        bottomBar = {
            BottomBarWithCurve(onAddClick = { showDialog = true }) // Ensure this is defined
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color.White)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(filteredCustomers) { customer ->
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

@Composable
fun BottomBarWithCurve(onAddClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF64B5F6), Color(0xFF1E88E5))
                )
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        FloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-30).dp)
                .size(56.dp),
            containerColor = Color.Blue
        ) {
            Icon(Icons.Default.Add, contentDescription = "Thêm khách hàng", tint = Color.White)
        }
    }
}
