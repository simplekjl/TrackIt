package com.simplekjl.domain.usecase

import com.simplekjl.domain.model.Profile
import com.simplekjl.domain.repository.Repository
import com.simplekjl.domain.utils.FlowUseCase
import com.simplekjl.domain.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProfileUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: Repository
) :
    FlowUseCase<Unit, Result<Profile>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<Profile>> {
        return flow {
            try {
                val result = repository.getProfile()
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }
}
