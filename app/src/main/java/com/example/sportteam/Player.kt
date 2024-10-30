package com.example.sportteam

data class Player(
    val id: Int,
    val name: String,
    val number: Int,
    val position: String,
    val age: Int,
    val imageRes: Int,
    val country: String,
    val previousTeam: String,
    val isLaMasiaGraduate: Boolean,
    val stats: PlayerStats

)