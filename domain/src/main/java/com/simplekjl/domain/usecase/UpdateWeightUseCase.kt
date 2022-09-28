package com.simplekjl.domain.usecase

import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.repository.Repository
import com.simplekjl.domain.utils.FlowUseCase
import com.simplekjl.domain.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateWeightUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: Repository
) :
    FlowUseCase<Weight, Result<Unit>>(dispatcher) {
    override fun execute(parameters: Weight): Flow<Result<Unit>> {
        return flow {
            try {
                val result = repository.updateWeight(parameters)
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }
}
