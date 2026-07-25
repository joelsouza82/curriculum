package com.example.curriculum.data.remote

import com.example.curriculum.data.model.Personal
import retrofit2.http.GET

interface ApiService {
    @GET("/personals")
    suspend fun fetchPersonal(): List<Personal>
}
