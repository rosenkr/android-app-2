package se.umu.alro0113.trackandbet.features.onboarding.presentation.welcome_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.features.onboarding.data.datastore.DataStoreRepository
import javax.inject.Inject

// Although the consistent approach of the project is to have separate State files
// for packages with a ViewModel, note that here the state management is instead
// delegated to the DataStore repository through the function provided below
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