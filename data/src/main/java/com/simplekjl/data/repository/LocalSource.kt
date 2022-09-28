package com.simplekjl.data.repository

import com.simplekjl.data.model.MeasuresRaw
import com.simplekjl.data.model.ProfileRaw
import com.simplekjl.data.model.WeightRaw

interface LocalSource {
    fun getAllWeights(): List<WeightRaw>
    fun getAllWeightsFromTo(startDate: Long?, endDate: Long?): List<WeightRaw>
    fun findWeightByDate(date: Long): WeightRaw
    fun insertAllWeights(vararg weightsRaw: WeightRaw)
    fun deleteWeight(weightRaw: WeightRaw)
    fun updateWeight(weightRaw: WeightRaw)
    fun getAllMeasures(): List<MeasuresRaw>
    fun getMeasureByDate(date: Long): MeasuresRaw
    fun getAllMeasurementsFromTo(startDate: Long?, endDate: Long?): List<MeasuresRaw>
    fun insertAllMeasures(vararg measures: MeasuresRaw)
    fun deleteMeasure(measuresRaw: MeasuresRaw)
    fun updateMeasure(measuresRaw: MeasuresRaw)
    fun getProfile(): ProfileRaw
}
