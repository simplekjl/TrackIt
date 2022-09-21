package com.simplekjl.data.repository

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.domain.model.Measures
import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.repository.LocalSource
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

    private val weight: Weight = fixture()
    private val measures: Measures = fixture()

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
        every { localSource.findWeightByDate(weight.date) } returns weight
        val result = repository.findWeightByDate(weight.date)
        assert(result.date == weight.date)
    }

    @Test
    fun insertAllWeights() {
        every { localSource.insertAllWeights(weight) } returns Unit
        repository.insertAllWeights(weight)
        verify { localSource.insertAllWeights(weight) }
    }

    @Test
    fun deleteWeight() {
        every { localSource.deleteWeight(weight) } returns Unit
        every { localSource.getAllWeights() } returns emptyList()
        val result = repository.getAllWeights()
        assert(result.isEmpty())
    }

    @Test
    fun updateWeight() {
        every { localSource.updateWeight(weight) } returns Unit
        every { localSource.findWeightByDate(weight.date) } returns weight.copy(uid = 1)
        val result = repository.findWeightByDate(weight.date)
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
        every { localSource.getMeasureByDate(measures.date) } returns measures
        val result = repository.getMeasureByDate(measures.date)
        assert(result.date == measures.date)
    }

    @Test
    fun getAllMeasurementsFromTo() {
        every { localSource.getAllMeasurementsFromTo(100L, 1001L) } returns listOf(
            measures
        )
        val result = repository.getAllMeasurementsFromTo(100L, 1001L)
        assert(result.size == 1)
    }

    @Test
    fun insertAllMeasures() {
        every { localSource.insertAllMeasures(measures) } returns Unit
        every { localSource.getMeasureByDate(measures.date) } returns measures
        repository.insertAllMeasures(measures)
        val result = repository.getMeasureByDate(measures.date)
        assert(result.date == measures.date)
    }

    @Test
    fun deleteMeasure() {
        every { localSource.deleteMeasure(measures) } returns Unit
        repository.deleteMeasure(measures)
        verify { localSource.deleteMeasure(measures) }
    }

    @Test
    fun updateMeasure() {
        every { localSource.updateMeasure(measures) } returns Unit
        repository.updateMeasure(measures)
        verify { repository.updateMeasure(measures) }
    }
}
