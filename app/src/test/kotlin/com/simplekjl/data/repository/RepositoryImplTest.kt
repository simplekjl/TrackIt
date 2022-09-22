package com.simplekjl.data.repository

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.data.mapper.toModel
import com.simplekjl.data.model.MeasuresRaw
import com.simplekjl.data.model.WeightRaw
import com.simplekjl.domain.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class RepositoryImplTest {
    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @MockK
    private lateinit var localSource: LocalSource

    private lateinit var repository: Repository

    private val weightRaw: WeightRaw = fixture()
    private val measuresRaw: MeasuresRaw = fixture()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = RepositoryImpl(localSource)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getAllWeights() {
        every { localSource.getAllWeights() } returns fixture()
        val result = repository.getAllWeights()
        assert(result.isNotEmpty())
    }

    @Test
    fun getAllWeightsFromTo() {
        every { localSource.getAllWeightsFromTo(100L, 1002L) } returns fixture()
        val result = repository.getAllWeightsFromTo(100L, 1002L)
        assert(result.isNotEmpty())
    }

    @Test
    fun findWeightByDate() {
        every { localSource.findWeightByDate(weightRaw.date) } returns weightRaw
        val result = repository.findWeightByDate(weightRaw.date)
        assert(result.date == weightRaw.date)
    }

    @Test
    fun insertAllWeights() {
        every { localSource.insertAllWeights(weightRaw) } returns Unit
        repository.insertAllWeights(weightRaw.toModel())
        verify { localSource.insertAllWeights(weightRaw) }
    }

    @Test
    fun deleteWeight() {
        every { localSource.deleteWeight(weightRaw) } returns Unit
        every { localSource.getAllWeights() } returns emptyList()
        val result = repository.getAllWeights()
        assert(result.isEmpty())
    }

    @Test
    fun updateWeight() {
        every { localSource.updateWeight(weightRaw) } returns Unit
        every { localSource.findWeightByDate(weightRaw.date) } returns weightRaw.copy(uid = 1)
        val result = repository.findWeightByDate(weightRaw.date)
        assert(result.uid == 1)
    }

    @Test
    fun getAllMeasures() {
        every { localSource.getAllMeasures() } returns fixture()
        val result = repository.getAllMeasures()
        assert(result.isNotEmpty())
    }

    @Test
    fun getMeasureByDate() {
        every { localSource.getMeasureByDate(measuresRaw.date) } returns measuresRaw
        val result = repository.getMeasureByDate(measuresRaw.date)
        assert(result.date == measuresRaw.date)
    }

    @Test
    fun getAllMeasurementsFromTo() {
        every { localSource.getAllMeasurementsFromTo(100L, 1001L) } returns listOf(
            measuresRaw
        )
        val result = repository.getAllMeasurementsFromTo(100L, 1001L)
        assert(result.size == 1)
    }

    @Test
    fun insertAllMeasures() {
        every { localSource.insertAllMeasures(measuresRaw) } returns Unit
        every { localSource.getMeasureByDate(measuresRaw.date) } returns measuresRaw
        repository.insertAllMeasures(measuresRaw.toModel())
        val result = repository.getMeasureByDate(measuresRaw.date)
        assert(result.date == measuresRaw.date)
    }

    @Test
    fun deleteMeasure() {
        every { localSource.deleteMeasure(measuresRaw) } returns Unit
        repository.deleteMeasure(measuresRaw.toModel())
        verify { localSource.deleteMeasure(measuresRaw) }
    }

    @Test
    fun updateMeasure() {
        every { localSource.updateMeasure(measuresRaw) } returns Unit
        repository.updateMeasure(measuresRaw.toModel())
        verify { repository.updateMeasure(measuresRaw.toModel()) }
    }
}
