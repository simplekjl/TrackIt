package com.simplekjl.trackit.framework

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.domain.model.Measures
import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.repository.LocalSource
import com.simplekjl.trackit.framework.database.WeightDao
import com.simplekjl.trackit.framework.database.WeightDatabase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class LocalSourceImplTest {

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @MockK
    private lateinit var weightDao: WeightDao

    @MockK
    private lateinit var database: WeightDatabase

    private lateinit var localSource: LocalSource

    private val weight: Weight = fixture()
    private val measures: Measures = fixture()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        localSource = LocalSourceImpl(database)
        every { database.getWeightDao() } returns weightDao
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getAllWeights() {
        every { database.getWeightDao().getAllWeights() } returns fixture()
        val result = localSource.getAllWeights()
        assert(result.isNotEmpty())
    }

    @Test
    fun getAllWeightsFromTo() {
        every { database.getWeightDao().getAllWeightsFromTo(100L, 1002L) } returns fixture()
        val result = localSource.getAllWeightsFromTo(100L, 1002L)
        assert(result.isNotEmpty())
    }

    @Test
    fun findWeightByDate() {
        every { database.getWeightDao().findWeightByDate(weight.date) } returns weight
        val result = localSource.findWeightByDate(weight.date)
        assert(result.date == weight.date)
    }

    @Test
    fun insertAllWeights() {
        every { database.getWeightDao().insertAllWeights(weight) } returns Unit
        localSource.insertAllWeights(weight)
        verify { database.getWeightDao().insertAllWeights(weight) }
    }

    @Test
    fun deleteWeight() {
        every { database.getWeightDao().deleteWeight(weight) } returns Unit
        every { database.getWeightDao().getAllWeights() } returns emptyList()
        val result = localSource.getAllWeights()
        assert(result.isEmpty())
    }

    @Test
    fun updateWeight() {
        every { database.getWeightDao().updateWeight(weight) } returns Unit
        every { database.getWeightDao().findWeightByDate(weight.date) } returns weight.copy(uid = 1)
        val result = localSource.findWeightByDate(weight.date)
        assert(result.uid == 1)
    }

    @Test
    fun getAllMeasures() {
        every { database.getWeightDao().getAllMeasures() } returns fixture()
        val result = localSource.getAllMeasures()
        assert(result.isNotEmpty())
    }

    @Test
    fun getMeasureByDate() {
        every { database.getWeightDao().getMeasureByDate(measures.date) } returns measures
        val result = localSource.getMeasureByDate(measures.date)
        assert(result.date == measures.date)
    }

    @Test
    fun getAllMeasurementsFromTo() {
        every { database.getWeightDao().getAllMeasurementsFromTo(100L, 1001L) } returns listOf(
            measures
        )
        val result = localSource.getAllMeasurementsFromTo(100L, 1001L)
        assert(result.size == 1)
    }

    @Test
    fun insertAllMeasures() {
        every { database.getWeightDao().insertAllMeasures(measures) } returns Unit
        every { database.getWeightDao().getMeasureByDate(measures.date) } returns measures
        localSource.insertAllMeasures(measures)
        val result = localSource.getMeasureByDate(measures.date)
        assert(result.date == measures.date)
    }

    @Test
    fun deleteMeasure() {
        every { database.getWeightDao().deleteMeasure(measures) } returns Unit
        localSource.deleteMeasure(measures)
        verify { database.getWeightDao().deleteMeasure(measures) }
    }

    @Test
    fun updateMeasure() {
        every { database.getWeightDao().updateMeasure(measures) } returns Unit
        localSource.updateMeasure(measures)
        verify { localSource.updateMeasure(measures) }
    }
}
