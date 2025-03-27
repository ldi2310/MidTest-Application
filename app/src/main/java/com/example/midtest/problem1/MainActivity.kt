package com.example.midtest.problem1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: CustomerViewModel = ViewModelProvider(this)[CustomerViewModel::class.java]

        setContent {
            CustomerScreen(viewModel)
        }
    }
}

