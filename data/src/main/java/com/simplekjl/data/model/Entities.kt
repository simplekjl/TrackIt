package com.simplekjl.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight")
data class WeightRaw(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "note") val note: String?
)

@Entity(tableName = "profile")
data class ProfileRaw(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "goalWeight")
    val goalWeight: Double,
    @ColumnInfo(name = "createdDate")
    val createdDate: Long
)

// User should be able to also create measurements without tracking weight
@Entity(tableName = "measures", primaryKeys = ["entryUid", "date"])
data class MeasuresRaw(
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
