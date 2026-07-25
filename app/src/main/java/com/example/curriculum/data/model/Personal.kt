package com.example.curriculum.data.model

import com.google.gson.annotations.SerializedName

data class Personal(
    @SerializedName("id_personal")
    val id: Int,
    val address: String,
    val city: String,
    val neighborhood: String,
    val state: String,
    val cep: String,
    val phone: String,
    val email: String,
    val website: String?,
    val linkedin: String?,
    val github: String?
)
