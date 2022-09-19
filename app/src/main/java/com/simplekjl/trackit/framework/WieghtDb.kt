package com.simplekjl.trackit.framework

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.simplekjl.domain.model.Weight
import com.simplekjl.trackit.framework.converters.Converters

@Database(entities = [Weight::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class WeightDatabase : RoomDatabase() {

    abstract fun getWeightDao(): WeightDao
}

@Dao
interface WeightDao {
    @Query("SELECT * FROM weight")
    fun getAll(): List<Weight>

    @Query("SELECT * FROM weight WHERE date IS :uid")
    fun findByDate(uid: Long): Weight

    @Insert
    fun insertAll(vararg weights: Weight)

    @Delete
    fun delete(weight: Weight)
}
