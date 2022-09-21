package com.simplekjl.trackit.framework

import com.simplekjl.domain.model.Measures
import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.repository.LocalSource
import com.simplekjl.trackit.framework.database.WeightDatabase

class LocalSourceImpl(private val database: WeightDatabase) : LocalSource {
    override fun getAllWeights(): List<Weight> = database.getWeightDao().getAllWeights()

    override fun getAllWeightsFromTo(startDate: Long?, endDate: Long?): List<Weight> {
        return database.getWeightDao().getAllWeightsFromTo(startDate, endDate)
    }

    override fun findWeightByDate(date: Long): Weight {
        return database.getWeightDao().findWeightByDate(date)
    }

    override fun insertAllWeights(vararg weights: Weight) {
        return database.getWeightDao().insertAllWeights(*weights)
    }

    override fun deleteWeight(weight: Weight) {
        database.getWeightDao().deleteWeight(weight)
    }

    override fun updateWeight(weight: Weight) {
        database.getWeightDao().updateWeight(weight)
    }

    override fun getAllMeasures(): List<Measures> {
        return database.getWeightDao().getAllMeasures()
    }

    override fun getMeasureByDate(date: Long): Measures {
        return database.getWeightDao().getMeasureByDate(date)
    }

    override fun getAllMeasurementsFromTo(startDate: Long?, endDate: Long?): List<Measures> {
        return database.getWeightDao().getAllMeasurementsFromTo(startDate, endDate)
    }

    override fun insertAllMeasures(vararg measures: Measures) {
        database.getWeightDao().insertAllMeasures(*measures)
    }

    override fun deleteMeasure(measures: Measures) {
        database.getWeightDao().deleteMeasure(measures)
    }

    override fun updateMeasure(measures: Measures) {
        database.getWeightDao().updateMeasure(measures)
    }
}
