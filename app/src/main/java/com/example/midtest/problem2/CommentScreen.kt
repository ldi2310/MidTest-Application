package com.example.midtest.problem2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentScreen(viewModel: CommentViewModel = viewModel()) {
    val comments by viewModel.comments

    // TopBar outside of Column
    Column(modifier = Modifier.fillMaxSize()) {
        // TopBar with rounded corners and shadow effect
        TopAppBar(
            title = {
                Text(
                    text = "ZAHOO",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color(0xFF1877F2), // Blue like Facebook
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)) // Shadow with rounded corners
                .background(Color.White), // White background for TopBar
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White) // TopBar color
        )

        // Content Column for the list of comments
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF0F2F5)) // Light background to mimic Facebook's design
        ) {
            LazyColumn(
            ) {
                items(comments) { comment ->
                    Card(
                        modifier = Modifier

                            .fillMaxWidth()
                            .padding(8.dp)
                            .shadow(8.dp, RoundedCornerShape(24.dp))
                            .clip(RoundedCornerShape(8))
                            .background(Color.White)
                            .border(1.dp, Color.Gray, RoundedCornerShape(24.dp)) // Border with rounded corners
                    ) {
                        Row(
                            modifier = Modifier
                                .background(Color(0xFFF1F3F5)) // Light background for the comment section
                                .padding(16.dp)
                        ) {
                            // Avatar section with first letter and background
                            val firstLetter = comment.name.first().toString().uppercase()
                            val backgroundColor = generateColor(comment)

                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(50)) // Clip to a circle
                                    .background(backgroundColor)
                                    .border(1.dp, Color.Gray, RoundedCornerShape(50)) // Circle border with rounded corners
                                    .padding(8.dp) // Padding for the content inside the circle
                            ) {
                                Text(
                                    text = firstLetter,
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }

                            // Name, Email, and Comment body
                            Column(modifier = Modifier.fillMaxWidth().padding(start = 16.dp)) {
                                // Name and Email section with borders
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 8.dp)
                                        .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)) // Rounded corners
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = comment.name,
                                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = comment.email,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray
                                    )
                                }

                                // Comment body text with a bit of space and lighter background
                                Text(
                                    text = comment.body,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(top = 4.dp),
                                    color = Color(0xFF212121) // Dark text color for the body
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun generateColor(comment: Comment): Color {
    val colors = listOf(
        Color(0xFF3F51B5), Color(0xFFFF4081), Color(0xFF4CAF50),
        Color(0xFFFFC107), Color(0xFF9C27B0), Color(0xFF00BCD4),
        Color(0xFFFF5722), Color(0xFF607D8B)
    )

    // Make sure the index is non-negative
    val index = (comment.hashCode() % colors.size + colors.size) % colors.size
    return colors[index]
}