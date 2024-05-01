package com.turk.mygalleryapp.core.functional

sealed class Either<out L, out R> {
    data class Error<out L>(val error: L) : Either<L, Nothing>()

    data class Success<out R>(val response: R) : Either<Nothing, R>()

    fun <L> error(a: L) = Error(a)
    fun <R> success(b: R) = Success(b)

    fun either(fnError: (L) -> Any, fnSuccess: (R) -> Unit): Any =
        when (this) {
            is Error -> fnError(error)
            is Success -> fnSuccess(response)
        }

}