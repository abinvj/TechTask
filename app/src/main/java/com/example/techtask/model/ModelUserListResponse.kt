package com.example.techtask.model

data class ModelUserListResponse(
    val page: Long,
    val per_page: Long,
    val total: Long,
    val total_pages: Long,
    val data: List<Data>,
    val support: Support,
)