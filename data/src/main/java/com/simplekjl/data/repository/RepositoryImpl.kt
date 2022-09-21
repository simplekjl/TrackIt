package com.simplekjl.data.repository

import com.simplekjl.domain.model.Measures
import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.repository.LocalSource
import com.simplekjl.domain.repository.Repository

class RepositoryImpl(
    private val localSource: LocalSource
) : Repository {
    override fun getAllWeights(): List<Weight> {
        return localSource.getAllWeights()
    }

    override fun getAllWeightsFromTo(startDate: Long?, endDate: Long?): Int {
        return localSource.getAllWeightsFromTo(startDate, endDate)
    }

    override fun findWeightByDate(date: Long): Weight {
        return localSource.findWeightByDate(date)
    }

    override fun insertAllWeights(vararg weights: Weight) {
        return localSource.insertAllWeights(*weights)
    }

    override fun deleteWeight(weight: Weight) {
        localSource.deleteWeight(weight)
    }

    override fun updateWeight(weight: Weight) {
        localSource.updateWeight(weight)
    }

    override fun getAllMeasures(): List<Measures> {
        return localSource.getAllMeasures()
    }

    override fun getMeasureByDate(date: Long): Measures {
        return localSource.getMeasureByDate(date)
    }

    override fun getAllMeasurementsFromTo(startDate: Long?, endDate: Long?): Int {
        return localSource.getAllMeasurementsFromTo(startDate, endDate)
    }

    override fun insertAllMeasures(vararg measures: Measures) {
        localSource.insertAllMeasures(*measures)
    }

    override fun deleteMeasure(measures: Measures) {
        localSource.deleteMeasure(measures)
    }

    override fun updateMeasure(measures: Measures) {
        localSource.updateMeasure(measures)
    }
}
