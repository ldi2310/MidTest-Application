package com.example.midtest.problem2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class CommentViewModel : ViewModel() {
    private val _comments = mutableStateOf<List<Comment>>(emptyList())
    val comments: State<List<Comment>> = _comments

    init {
        fetchComments()
    }

    private fun fetchComments() {
        viewModelScope.launch {
            try {
                _comments.value = RetrofitInstance.api.getComments()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
