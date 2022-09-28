package com.simplekjl.domain.usecase

import com.simplekjl.domain.model.Weight
import com.simplekjl.domain.repository.Repository
import com.simplekjl.domain.utils.FlowUseCase
import com.simplekjl.domain.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWeightsUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val repository: Repository
) :
    FlowUseCase<Unit, Result<List<Weight>>>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Result<List<Weight>>> {
        return flow {
            try {
                val result = repository.getAllWeights()
                emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
    }
}
