package se.umu.alro0113.trackandbet.onboarding.presentation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import se.umu.alro0113.trackandbet.navigation.Screen
import se.umu.alro0113.trackandbet.onboarding.data.datastore.MyPreferencesDataStore
import javax.inject.Inject


@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val myPreferencesDataStore: MyPreferencesDataStore, private val savedStateHandle: SavedStateHandle
): ViewModel() {

    companion object {
        private const val CURRENT_ROUTE_KEY = "current_route"
    }

    private val _currentRoute: MutableStateFlow<Screen> = MutableStateFlow(
        savedStateHandle.get<String>(CURRENT_ROUTE_KEY)?.let {
            Json.decodeFromString(it)
        } ?: Screen.HomeScreen
    )
    val currentRoute: StateFlow<Screen> = _currentRoute.asStateFlow()

    fun setCurrentRoute(route: Screen) {
        _currentRoute.update { route }
        savedStateHandle[CURRENT_ROUTE_KEY] = Json.encodeToString(route)
    }


    var isLoading by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf<Screen>(Screen.OnBoardingScreen)
        private set
    init {
        myPreferencesDataStore.readAppEntry.onEach { loadOnBoardingScreen ->
           startDestination = if (loadOnBoardingScreen) {
               Screen.OnBoardingScreen
           } else {
               _currentRoute.value
           }
            delay(100) // Delay is problem for recomposition of homescreen, homescreen recomposes while splash screen is happening if delay is too high?
            isLoading = false
        }.launchIn(viewModelScope)
    }

    fun saveAppEntry() {
        viewModelScope.launch {
            myPreferencesDataStore.saveAppEntry()
        }
    }
}