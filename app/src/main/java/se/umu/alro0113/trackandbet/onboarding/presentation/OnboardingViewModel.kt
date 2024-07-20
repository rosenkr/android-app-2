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
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.onboarding.data.datastore.MyPreferencesDataStore
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val myPreferencesDataStore: MyPreferencesDataStore
): ViewModel() {
    var isLoading by mutableStateOf(true)
        private set

    // TODO perhaps to be made to point to Serializable screen object OnBoardingScreen  initially ?
    var startDestination by mutableStateOf<Screen>(Screen.OnBoardingScreen)
        private set

    init {
        myPreferencesDataStore.readAppEntry.onEach { loadOnBoardingScreen ->
           startDestination = if (loadOnBoardingScreen) {
               Screen.OnBoardingScreen // TODO remove Screen enums, instead do = OnBoardingScreen obj
           } else {
               Screen.HomeScreen // TODO same here
           }
            delay(100) // TODO delay is problem for recomposition of homescreen, homescreen recomposes while splash screen is happening if delay is too high?
            isLoading = false
        }.launchIn(viewModelScope)
    }

    fun saveAppEntry() {
        viewModelScope.launch {
            myPreferencesDataStore.saveAppEntry()
        }
    }
}