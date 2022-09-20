package com.simplekjl.domain.utils

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

/**
 * Executes business logic synchronously or asynchronously using a non cancellable context.
 * Should be use for task that need to be executed even after the scope is cancelled
 */
abstract class NonCancellableUseCase<in P, out R> {
    /**
     * Executes the use case on NonCancellable context and returns a result [R].
     *
     * @return a result [R].
     *
     * @param parameters the input parameters [P] to run the use case with
     */
    suspend operator fun invoke(parameters: P): R {
        return withContext(NonCancellable) {
            execute(parameters)
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}
