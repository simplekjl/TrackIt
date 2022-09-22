package com.simplekjl.trackit.framework

import com.simplekjl.data.model.MeasuresRaw
import com.simplekjl.data.model.WeightRaw
import com.simplekjl.data.repository.LocalSource
import com.simplekjl.trackit.framework.database.WeightDatabase

class LocalSourceImpl(private val database: WeightDatabase) : LocalSource {
    override fun getAllWeights(): List<WeightRaw> = database.getWeightDao().getAllWeights()

    override fun getAllWeightsFromTo(startDate: Long?, endDate: Long?): List<WeightRaw> {
        return database.getWeightDao().getAllWeightsFromTo(startDate, endDate)
    }

    override fun findWeightByDate(date: Long): WeightRaw {
        return database.getWeightDao().findWeightByDate(date)
    }

    override fun insertAllWeights(vararg weightsRaw: WeightRaw) {
        return database.getWeightDao().insertAllWeights(*weightsRaw)
    }

    override fun deleteWeight(weightRaw: WeightRaw) {
        database.getWeightDao().deleteWeight(weightRaw)
    }

    override fun updateWeight(weightRaw: WeightRaw) {
        database.getWeightDao().updateWeight(weightRaw)
    }

    override fun getAllMeasures(): List<MeasuresRaw> {
        return database.getWeightDao().getAllMeasures()
    }

    override fun getMeasureByDate(date: Long): MeasuresRaw {
        return database.getWeightDao().getMeasureByDate(date)
    }

    override fun getAllMeasurementsFromTo(startDate: Long?, endDate: Long?): List<MeasuresRaw> {
        return database.getWeightDao().getAllMeasurementsFromTo(startDate, endDate)
    }

    override fun insertAllMeasures(vararg measures: MeasuresRaw) {
        database.getWeightDao().insertAllMeasures(*measures)
    }

    override fun deleteMeasure(measuresRaw: MeasuresRaw) {
        database.getWeightDao().deleteMeasure(measuresRaw)
    }

    override fun updateMeasure(measuresRaw: MeasuresRaw) {
        database.getWeightDao().updateMeasure(measuresRaw)
    }
}
