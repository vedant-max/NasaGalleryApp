package co.vance.nasagalleryapp.domain

import co.vance.nasagalleryapp.data.NasaRepository
import co.vance.nasagalleryapp.data.service.NasaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
  @Provides
  @ViewModelScoped
  fun provideRepository(
    service: NasaService,
  ): NasaRepository = NasaRepository(service)
}