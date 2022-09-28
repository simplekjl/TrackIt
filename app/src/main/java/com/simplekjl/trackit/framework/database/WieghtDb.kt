package com.simplekjl.trackit.framework.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import com.simplekjl.data.model.MeasuresRaw
import com.simplekjl.data.model.ProfileRaw
import com.simplekjl.data.model.WeightRaw
import com.simplekjl.trackit.framework.database.converters.Converters

@Database(
    entities = [WeightRaw::class, MeasuresRaw::class, ProfileRaw::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class WeightDatabase : RoomDatabase() {

    abstract fun getWeightDao(): WeightDao
    abstract fun getProfileDao(): ProfileDao
}

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getProfile(): ProfileRaw
}

@Dao
interface WeightDao {
    @Query("SELECT * FROM weight")
    fun getAllWeights(): List<WeightRaw>

    @Query("SELECT * FROM weight WHERE date BETWEEN :startDate AND :endDate")
    fun getAllWeightsFromTo(startDate: Long?, endDate: Long?): List<WeightRaw>

    @Query("SELECT * FROM weight WHERE date IS :date")
    fun findWeightByDate(date: Long): WeightRaw

    @Insert
    fun insertAllWeights(vararg weightsRaw: WeightRaw)

    @Delete
    fun deleteWeight(weightRaw: WeightRaw)

    @Update
    fun updateWeight(weightRaw: WeightRaw)

    @Query("SELECT * FROM measures")
    fun getAllMeasures(): List<MeasuresRaw>

    @Query("SELECT * FROM measures WHERE date IS :date")
    fun getMeasureByDate(date: Long): MeasuresRaw

    @Query("SELECT * FROM measures WHERE date BETWEEN :startDate AND :endDate")
    fun getAllMeasurementsFromTo(startDate: Long?, endDate: Long?): List<MeasuresRaw>

    @Insert
    fun insertAllMeasures(vararg measures: MeasuresRaw)

    @Delete
    fun deleteMeasure(measuresRaw: MeasuresRaw)

    @Update
    fun updateMeasure(measuresRaw: MeasuresRaw)
}
