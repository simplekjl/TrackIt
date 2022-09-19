package com.simplekjl.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weight")
data class Weight(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "weight") val weight: Double,
    @ColumnInfo(name = "note") val note: String?
)
