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
@Entity(tableName = "measures")
data class MeasuresRaw(
    @PrimaryKey(autoGenerate = true)
    val entryUid: Int,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "chest")
    val chest: Double,
    @ColumnInfo(name = "leftArm")
    val leftArm: Double,
    @ColumnInfo(name = "rightArm")
    val rightArm: Double,
    @ColumnInfo(name = "waist")
    val waist: Double,
    @ColumnInfo(name = "hips")
    val hips: Double,
    @ColumnInfo(name = "leftThigh")
    val leftThigh: Double,
    @ColumnInfo(name = "rightThigh")
    val rightThigh: Double,
    @ColumnInfo(name = "leftCalf")
    val leftCalf: Double,
    @ColumnInfo(name = "rightCalf")
    val rightCalf: Double
)
