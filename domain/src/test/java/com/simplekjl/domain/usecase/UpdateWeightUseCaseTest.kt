package com.simplekjl.domain.usecase

import app.simplekjl.test.util.MainCoroutineRule
import com.appmattus.kotlinfixture.decorator.nullability.NeverNullStrategy
import com.appmattus.kotlinfixture.decorator.nullability.nullabilityStrategy
import com.appmattus.kotlinfixture.kotlinFixture
import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.repository.Repository
import com.simplekjl.domain.utils.Result
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class UpdateWeightUseCaseTest {
    private val fixture = kotlinFixture {
        nullabilityStrategy(NeverNullStrategy)
    }

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var repository: Repository
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var useCase: UpdateWeightUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        useCase = UpdateWeightUseCase(dispatcher, repository)
    }

    @Test
    fun `call repository when using usecase`() = runBlockingTest {
        val weightRaw: Weight = fixture()
        every { repository.updateWeight(weightRaw) } returns Unit
        val resultFlow = useCase(weightRaw).first()
        verify { repository.updateWeight(weightRaw) }
        assert(resultFlow is Result.Success<*>)
    }

    @Test
    fun `call repository returns exception`() = runBlockingTest {
        val weightRaw: Weight = fixture()
        every { repository.updateWeight(weightRaw) } throws Exception("")
        val resultFlow = useCase(weightRaw).first()
        verify { repository.updateWeight(weightRaw) }
        assert(resultFlow is Result.Error)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
