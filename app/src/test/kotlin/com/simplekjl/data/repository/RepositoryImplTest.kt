package com.simplekjl.data.repository

import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.trackit.framework.database.WeightDatabase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class RepositoryImplTest {

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @MockK
    private lateinit var database: WeightDatabase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getAllWeights() {
    }

    @Test
    fun getAllWeightsFromTo() {
    }

    @Test
    fun findWeightByDate() {
    }

    @Test
    fun insertAllWeights() {
    }

    @Test
    fun deleteWeight() {
    }

    @Test
    fun updateWeight() {
    }

    @Test
    fun getAllMeasures() {
    }

    @Test
    fun getMeasureByDate() {
    }

    @Test
    fun getAllMeasurementsFromTo() {
    }

    @Test
    fun insertAllMeasures() {
    }

    @Test
    fun deleteMeasure() {
    }

    @Test
    fun updateMeasure() {
    }
}
