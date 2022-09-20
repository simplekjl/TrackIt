package com.simplekjl.trackit.framework

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import com.simplekjl.domain.model.Measures
import com.simplekjl.domain.model.Weight
import com.simplekjl.trackit.framework.converters.Converters

@Database(entities = [Weight::class, Measures::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class WeightDatabase : RoomDatabase() {

    abstract fun getWeightDao(): WeightDao
}

@Dao
interface WeightDao {
    @Query("SELECT * FROM weight")
    fun getAllWeights(): List<Weight>

    @Query("SELECT * FROM weight WHERE date BETWEEN :startDate AND :endDate")
    fun getAllWeightsFromTo(startDate: Long?, endDate: Long?): Int

    @Query("SELECT * FROM weight WHERE date IS :date")
    fun findWeightByDate(date: Long): Weight

    @Insert
    fun insertAllWeights(vararg weights: Weight)

    @Delete
    fun deleteWeight(weight: Weight)

    @Update
    fun updateWeight(weight: Weight)

    @Query("SELECT * FROM measures")
    fun getAllMeasures(): List<Measures>

    @Query("SELECT * FROM measures WHERE date IS :date")
    fun getMeasureByDate(date: Long): Measures

    @Query("SELECT * FROM measures WHERE date BETWEEN :startDate AND :endDate")
    fun getAllMeasurementsFromTo(startDate: Long?, endDate: Long?): Int

    @Insert
    fun insertAllMeasures(vararg measures: Measures)

    @Delete
    fun deleteMeasure(measures: Measures)

    @Update
    fun updateMeasure(measures: Measures)
}
