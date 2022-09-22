package com.simplekjl.domain.model

data class Weight(
    val uid: Int? = null,
    val date: Long,
    val weight: Double,
    val note: String?
)

data class Measures(
    val entryUid: Int,
    val date: Long,
    val chest: Double,
    val leftArm: Double,
    val rightArm: Double,
    val waist: Double,
    val hips: Double,
    val leftThigh: Double,
    val rightThigh: Double,
    val leftCalf: Double,
    val rightCalf: Double
)
