package co.vance.nasagalleryapp.data

import co.vance.nasagalleryapp.data.service.NasaService
import co.vance.nasagalleryapp.util.DataState
import co.vance.nasagalleryapp.util.parse
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NasaRepository @Inject constructor(private val service: NasaService) {

  fun getNasa() = flow {
    emit(DataState.Loading())
    service.getNasa().parse { nasa ->
      emit(DataState.Success(nasa))
    }
  }.catch {
    emit(DataState.Error(it))
  }

}