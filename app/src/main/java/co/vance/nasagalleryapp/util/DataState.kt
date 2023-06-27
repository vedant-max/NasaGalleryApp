package co.vance.nasagalleryapp.util

sealed class DataState<T>(
  val data: T? = null,
  val error: Throwable? = null
) {
  class Success<T>(data: T) : DataState<T>(data)
  class Loading<T>(data: T? = null) : DataState<T>(data)
  class Error<T>(throwable: Throwable, data: T? = null) : DataState<T>(data, throwable)
}