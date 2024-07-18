package se.umu.alro0113.trackandbet.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.onboarding.data.datastore.MyPreferencesDataStore
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val myPreferencesDataStore: MyPreferencesDataStore
): ViewModel() {
    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf("")
        private set
    // todo -> Change this later

    init {
        myPreferencesDataStore.readAppEntry.onEach { loadOnBoardingScreen ->
            // todo
        }
    }

    fun saveAppEntry() {
        viewModelScope.launch {
            myPreferencesDataStore.saveAppEntry()
        }
    }
}