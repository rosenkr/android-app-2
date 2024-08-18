package se.umu.alro0113.trackandbet.features.onboarding.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.features.onboarding.data.datastore.DataStoreRepository
import javax.inject.Inject

// State management with DataStore. IO Dispatcher inside viewmodelscope for accessing storage
@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {
    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOnBoardingState(completed = completed)
        }
    }
}