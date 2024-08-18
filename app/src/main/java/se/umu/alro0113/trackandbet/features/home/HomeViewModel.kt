package se.umu.alro0113.trackandbet.features.home

import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// The only place where I use SavedStateHandle to stored latitude and longitude
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState> = _state

    init {
        Log.d("ViewModel", "home ViewModel created: ${this.hashCode()}")

        // Restore state or initialize with default values
        val latitude = savedStateHandle.get<Double>("latitude") ?: 0.0
        val longitude = savedStateHandle.get<Double>("longitude") ?: 0.0

        Log.d("ViewModel", "Restored latitude: $latitude, longitude: $longitude")

        _state.value = HomeViewState(latitude = latitude, longitude = longitude)
    }

    private fun saveState(latitude: Double, longitude: Double) {

        savedStateHandle.set("latitude", latitude)
        savedStateHandle.set("longitude", longitude)

        Log.d("ViewModel", "Saved latitude: $latitude, longitude: $longitude")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("ViewModel", "home ViewModel cleared: ${this.hashCode()}")
    }

    fun setLocation(coords: Location) {
        val latitude = coords.latitude
        val longitude = coords.longitude
        _state.postValue(_state.value?.copy(latitude = latitude, longitude = longitude))
        saveState(latitude, longitude)
    }
}
