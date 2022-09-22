package com.simplekjl.trackit.framework

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.data.model.MeasuresRaw
import com.simplekjl.data.model.WeightRaw
import com.simplekjl.data.repository.LocalSource
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

    private val weightRaw: WeightRaw = fixture()
    private val measuresRaw: MeasuresRaw = fixture()

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
        every { database.getWeightDao().findWeightByDate(weightRaw.date) } returns weightRaw
        val result = localSource.findWeightByDate(weightRaw.date)
        assert(result.date == weightRaw.date)
    }

    @Test
    fun insertAllWeights() {
        every { database.getWeightDao().insertAllWeights(weightRaw) } returns Unit
        localSource.insertAllWeights(weightRaw)
        verify { database.getWeightDao().insertAllWeights(weightRaw) }
    }

    @Test
    fun deleteWeight() {
        every { database.getWeightDao().deleteWeight(weightRaw) } returns Unit
        every { database.getWeightDao().getAllWeights() } returns emptyList()
        val result = localSource.getAllWeights()
        assert(result.isEmpty())
    }

    @Test
    fun updateWeight() {
        every { database.getWeightDao().updateWeight(weightRaw) } returns Unit
        every { database.getWeightDao().findWeightByDate(weightRaw.date) } returns weightRaw.copy(uid = 1)
        val result = localSource.findWeightByDate(weightRaw.date)
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
        every { database.getWeightDao().getMeasureByDate(measuresRaw.date) } returns measuresRaw
        val result = localSource.getMeasureByDate(measuresRaw.date)
        assert(result.date == measuresRaw.date)
    }

    @Test
    fun getAllMeasurementsFromTo() {
        every { database.getWeightDao().getAllMeasurementsFromTo(100L, 1001L) } returns listOf(
            measuresRaw
        )
        val result = localSource.getAllMeasurementsFromTo(100L, 1001L)
        assert(result.size == 1)
    }

    @Test
    fun insertAllMeasures() {
        every { database.getWeightDao().insertAllMeasures(measuresRaw) } returns Unit
        every { database.getWeightDao().getMeasureByDate(measuresRaw.date) } returns measuresRaw
        localSource.insertAllMeasures(measuresRaw)
        val result = localSource.getMeasureByDate(measuresRaw.date)
        assert(result.date == measuresRaw.date)
    }

    @Test
    fun deleteMeasure() {
        every { database.getWeightDao().deleteMeasure(measuresRaw) } returns Unit
        localSource.deleteMeasure(measuresRaw)
        verify { database.getWeightDao().deleteMeasure(measuresRaw) }
    }

    @Test
    fun updateMeasure() {
        every { database.getWeightDao().updateMeasure(measuresRaw) } returns Unit
        localSource.updateMeasure(measuresRaw)
        verify { localSource.updateMeasure(measuresRaw) }
    }
}
