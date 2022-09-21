package com.simplekjl.domain.repository

import com.simplekjl.domain.model.Measures
import com.simplekjl.domain.model.Weight

interface Repository {
    fun getAllWeights(): List<Weight>
    fun getAllWeightsFromTo(startDate: Long?, endDate: Long?): List<Weight>
    fun findWeightByDate(date: Long): Weight
    fun insertAllWeights(vararg weights: Weight)
    fun deleteWeight(weight: Weight)
    fun updateWeight(weight: Weight)
    fun getAllMeasures(): List<Measures>
    fun getMeasureByDate(date: Long): Measures
    fun getAllMeasurementsFromTo(startDate: Long?, endDate: Long?): List<Measures>
    fun insertAllMeasures(vararg measures: Measures)
    fun deleteMeasure(measures: Measures)
    fun updateMeasure(measures: Measures)
}
