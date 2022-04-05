package com.example.issueproject.dto

data class RoomChildListResult(
    val id: String,
    val school: String,
    val room: String,
    val parent_num: String,
    val child_name: String,
    val child_age: String,
    val child_image: String,
    val spec: String
)
