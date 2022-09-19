package com.simplekjl.trackit.framework

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.simplekjl.domain.model.Weight
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

    private lateinit var userDao: WeightDao
    private lateinit var db: WeightDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, WeightDatabase::class.java
        ).build()
        userDao = db.getWeightDao()
    }

    @Test
    fun writeUserAndReadInList() {
        createDb()
        val time = System.currentTimeMillis()
        val user: Weight = Weight(12, time, 45.0, "")
        userDao.insertAll(user)
        val byName = userDao.findByDate(time)
        assertThat(byName.weight == user.weight).isTrue()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}
