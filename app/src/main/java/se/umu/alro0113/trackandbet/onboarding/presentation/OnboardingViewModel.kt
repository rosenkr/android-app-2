package se.umu.alro0113.trackandbet.onboarding.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.onboarding.data.datastore.MyPreferencesDataStore
import se.umu.alro0113.trackandbet.onboarding.presentation.navigation.Screen
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val myPreferencesDataStore: MyPreferencesDataStore
): ViewModel() {
    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Screen.OnBoardingScreen.name)
        private set

    init {
        myPreferencesDataStore.readAppEntry.onEach { loadOnBoardingScreen ->
           startDestination = if (loadOnBoardingScreen) {
               Screen.OnBoardingScreen.name
           } else {
               Screen.HomeScreen.name
           }
            delay(300)
            isLoading = false
        }.launchIn(viewModelScope)
    }

    fun saveAppEntry() {
        viewModelScope.launch {
            myPreferencesDataStore.saveAppEntry()
        }
    }
}