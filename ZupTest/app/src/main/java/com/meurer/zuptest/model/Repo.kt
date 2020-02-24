package com.meurer.zuptest.model

data class Repo(
    val id: Int,
    val name: String,
    val fullname: String?,
    val description: String?,
    val owner: Owner
)