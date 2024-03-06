package com.kinoyamboladmin.models

data class Movie(
    val id: Int,
    val image: String,
    val name: String,
    val description: String,
    val genders: List<String>,
    val schedules: List<Schedule>,
    val duration: Int,
    val trailerLink: String,
    val price: Double
)