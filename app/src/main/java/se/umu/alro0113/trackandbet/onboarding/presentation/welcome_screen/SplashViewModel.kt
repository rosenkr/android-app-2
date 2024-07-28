package se.umu.alro0113.trackandbet.onboarding.presentation.welcome_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.onboarding.data.datastore.DataStoreRepository
import javax.inject.Inject

// to be compliant with the rest of the project, ideally the viewmodel should expose a
// state variable, through which all types of state can be accessed,
// however for simplicity, since there is only 1 state value startDestination, it is currently kept here
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