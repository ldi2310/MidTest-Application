package com.example.midtest.problem2

import retrofit2.http.GET

interface ApiService {
    @GET("comments")
    suspend fun getComments(): List<Comment>
}
