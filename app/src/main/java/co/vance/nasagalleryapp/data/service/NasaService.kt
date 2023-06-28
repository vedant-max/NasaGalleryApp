package co.vance.nasagalleryapp.data.service

import co.vance.nasagalleryapp.data.model.NasaImage
import retrofit2.Response
import retrofit2.http.GET

interface NasaService {

  @GET("nasa-pictures.json")
  suspend fun getNasa(): Response<List<NasaImage>>
}