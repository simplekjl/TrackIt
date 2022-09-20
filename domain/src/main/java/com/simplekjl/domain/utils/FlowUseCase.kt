package com.simplekjl.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

/**
 * Executes business logic synchronously or asynchronously using Coroutines Flow.
 */
abstract class FlowUseCase<in P, out R>(private val coroutineDispatcher: CoroutineDispatcher) {
    /**
     * Executes the use case returns a result Flow<[R]>.
     *
     * @return Flow<[R]>.
     *
     * @param parameters the input parameters [P] to run the use case with
     */

    operator fun invoke(parameters: P): Flow<R> {
        return execute(parameters).flowOn(coroutineDispatcher)
    }

    /**
     * Override this to set the code to be executed.
     */
    abstract fun execute(parameters: P): Flow<R>
}

operator fun <R> FlowUseCase<Unit, R>.invoke(): Flow<R> = this(Unit)