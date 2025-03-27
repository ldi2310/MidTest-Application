package com.example.midtest.problem2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CommentScreen(viewModel: CommentViewModel = viewModel()) {
    val comments by viewModel.comments

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Danh sách bình luận", style = MaterialTheme.typography.headlineMedium)

        LazyColumn {
            items(comments) { comment ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Tên: ${comment.name}", style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Email: ${comment.email}", style = MaterialTheme.typography.bodyMedium)
                        Text(text = comment.body, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
