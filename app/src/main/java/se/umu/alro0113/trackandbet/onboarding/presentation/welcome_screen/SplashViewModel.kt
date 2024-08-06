package se.umu.alro0113.trackandbet.onboarding.presentation.welcome_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.onboarding.data.datastore.DataStoreRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _startDestination: MutableState<Screen?> = mutableStateOf(null)
    val startDestination: State<Screen?> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
                if (completed) {
                    _startDestination.value = Screen.HomeScreen
                } else {
                    _startDestination.value = Screen.WelcomeScreen
                }
            }
        }
    }
}