package co.vance.nasagalleryapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.vance.nasagalleryapp.data.NasaRepository
import co.vance.nasagalleryapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val repository: NasaRepository
) : ViewModel() {

    fun getNasa() = viewModelScope.launch {
        repository.getNasa().collect { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    Log.d("Vedant","Loading...")
                }

                is DataState.Success -> {
                    val nasa = dataState.data
                    Log.d("Vedant","NASA: $nasa")
                }
                is DataState.Error -> {
                    val exception = dataState.error
                    Log.d("Vedant","Exception: $exception")
                }
            }
        }
    }
}