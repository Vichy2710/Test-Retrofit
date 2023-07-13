package com.example.myapplication.model

data class Tour(
    val Message: String?,
    val page: Int?,
    val per_page: Int?,
    val totalrecord: Int?,
    val total_pages: Int?,
    val data: MutableList<Tourist>?
)