package com.jbkalit.domain.model

data class PostDetail (
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val name: String,
    val username: String,
    val commentList: List<Comment>
)
