package com.simplekjl.data.repository

import com.simplekjl.data.mapper.toMeasureModel
import com.simplekjl.data.mapper.toModel
import com.simplekjl.data.mapper.toRaw
import com.simplekjl.domain.model.Measures
import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.repository.Repository

class RepositoryImpl(
    private val localSource: LocalSource
) : Repository {
    override fun getAllWeights(): List<Weight> {
        return localSource.getAllWeights().toModel()
    }

    override fun getAllWeightsFromTo(startDate: Long?, endDate: Long?): List<Weight> {
        return localSource.getAllWeightsFromTo(startDate, endDate).toModel()
    }

    override fun findWeightByDate(date: Long): Weight {
        return localSource.findWeightByDate(date).toModel()
    }

    override fun insertAllWeights(vararg weight: Weight) {
        return localSource.insertAllWeights(*weight.toRaw())
    }

    override fun deleteWeight(weight: Weight) {
        localSource.deleteWeight(weight.toRaw())
    }

    override fun updateWeight(weight: Weight) {
        localSource.updateWeight(weight.toRaw())
    }

    override fun getAllMeasures(): List<Measures> {
        return localSource.getAllMeasures().toMeasureModel()
    }

    override fun getMeasureByDate(date: Long): Measures {
        return localSource.getMeasureByDate(date).toModel()
    }

    override fun getAllMeasurementsFromTo(startDate: Long?, endDate: Long?): List<Measures> {
        return localSource.getAllMeasurementsFromTo(startDate, endDate).toMeasureModel()
    }

    override fun insertAllMeasures(vararg measures: Measures) {
        localSource.insertAllMeasures(*measures.toRaw())
    }

    override fun deleteMeasure(measures: Measures) {
        localSource.deleteMeasure(measures.toRaw())
    }

    override fun updateMeasure(measures: Measures) {
        localSource.updateMeasure(measures.toRaw())
    }
}
