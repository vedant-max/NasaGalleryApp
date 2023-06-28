package co.vance.nasagalleryapp.presentation.imageList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.vance.nasagalleryapp.data.NasaRepository
import co.vance.nasagalleryapp.data.model.Nasa
import co.vance.nasagalleryapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: NasaRepository
) : ViewModel() {

    private val _images: MutableLiveData<DataState<List<Nasa>>> = MutableLiveData()
    val images: LiveData<DataState<List<Nasa>>> get() = _images

    init {
        getImages()
    }

    fun getImages() {
        viewModelScope.launch {
            repository.getNasa().collect {
                _images.postValue(it)
            }
        }
    }
}