package com.simplekjl.trackit.framework

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.google.common.truth.Truth.assertThat
import com.simplekjl.data.model.MeasuresRaw
import com.simplekjl.data.model.WeightRaw
import com.simplekjl.trackit.framework.database.WeightDao
import com.simplekjl.trackit.framework.database.WeightDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
class WeightDatabaseTest {

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    private lateinit var weightDao: WeightDao
    private lateinit var db: WeightDatabase

    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, WeightDatabase::class.java
        ).build()
        weightDao = db.getWeightDao()
    }

    @Test
    fun writeWeightAndReadInList() {
        createDb()
        val time = System.currentTimeMillis()
        val weightRaw = WeightRaw(12, time, 45.0, "")
        weightDao.insertAllWeights(weightRaw)
        val byName = weightDao.findWeightByDate(time)
        assertThat(byName.weight == weightRaw.weight).isTrue()
    }

    @Test
    fun writeMeasureAndReadInList() {
        createDb()
        val time = System.currentTimeMillis()
        var measure: MeasuresRaw = fixture()
        measure = measure.copy(date = time)
        weightDao.insertAllMeasures(measure)
        val byName = weightDao.getMeasureByDate(time)
        assertThat(byName.leftArm == measure.leftArm).isTrue()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}
