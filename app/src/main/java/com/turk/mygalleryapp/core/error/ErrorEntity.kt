package com.turk.mygalleryapp.core.error



/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class ErrorEntity {
    data class Error(val ex:Exception) : ErrorEntity()
    object UNKOWN : ErrorEntity()
    object None : ErrorEntity()

}
