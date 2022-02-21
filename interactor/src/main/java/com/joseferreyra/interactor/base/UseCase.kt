package com.joseferreyra.interactor.base

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params>(
    private val scope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher
) where Type : Any? {
    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        scope.launch {
            lateinit var result: Either<Failure, Type>
            withContext(dispatcher) {
                result = run(params)
            }
            onResult(result)
        }
    }
}