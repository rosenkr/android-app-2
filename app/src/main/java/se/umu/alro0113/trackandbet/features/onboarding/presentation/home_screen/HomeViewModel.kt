package se.umu.alro0113.trackandbet.features.onboarding.presentation.home_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import se.umu.alro0113.trackandbet.di.WebSocketHttpClient
import se.umu.alro0113.trackandbet.features.onboarding.data.dto.WebSocketMessage
import java.net.ConnectException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val webSocketHttpClient: WebSocketHttpClient
) : ViewModel() {

    private val _state = MutableLiveData<HomeViewState>()
    val state: LiveData<HomeViewState> = _state

    // to retry connection if failed on start up
    private val retryIntervalMillis = 6000L
    private val maxRetries = 4

    init {
        _state.value = HomeViewState()
        connectToWebSocket()
    }

    // In the viewmodel scope with an IO dispatcher, calls suspending function to establish a connection.
    // This function handles the retrying of an connection establishment
    private fun connectToWebSocket() {
        viewModelScope.launch(Dispatchers.IO) {
            var retryCount = 0
            while (retryCount < maxRetries) {
                try {
                    establishConnection()
                    break // Exit retry loop on successful connection
                } catch (e: ConnectException) {
                    Log.e("wslog", "Connection refused, retrying...", e)
                    _state.postValue(_state.value?.copy(error = "Connection refused. Retrying..."))
                    retryCount++
                    delay(retryIntervalMillis) // Wait before retrying
                }
            }

            if (retryCount >= maxRetries) {
                _state.postValue(_state.value?.copy(error = "Failed to connect after multiple attempts."))
            }
        }
    }

    // With the modules singleton httpclient for websocket connections, attempt to access the server at host/port/path with HTTP
    // Propagates potential ConnectException so it can be handled in connectToWebSocket
    private suspend fun establishConnection() {
        try {
            val client = webSocketHttpClient.getClient()
            // TODO client below
            //client.webSocket(method = HttpMethod.Get, host = "10.0.2.2", port = 8081, path = "/ws") {
            // TODO ec2 instance attempt ws://16.171.253.210:8081/ws
            // this instance is now deleted and inactive.
            client.webSocket(method = HttpMethod.Get, host = "16.171.253.210", port = 8081, path = "/ws"){
                handleConnection(this)
            }
        } catch (e: ConnectException) {
            throw e
        } catch (e: Exception) {
            Log.d("wslog", "error", e)
            _state.postValue(_state.value?.copy(error = "WebSocket setup error"))
        }
    }

    // Given an active session, handles the logic. In an infinite loop, read frames sent in the incoming channel.
    // Frame is expected to be Frame.Text. Converts it to a String, deserializes it into a WebSocketMessage.
    // The message has a type property that tells the receiver what to do with the object.
    // Depending on what type the server set when sending the frame, we either interpret the data as
    // a list of TickerData (in our case hardcoded to Apple's stock ticker AAPL)
    // or a single TickerData object.
    // In the previous case, set the state's dataset to be the message's data property
    // In the latter case, we first make the dataset of the state mutable, then add the single data item to it,
    // then call copy function to set dataset reference to the updated list of data
    private suspend fun handleConnection(session: DefaultClientWebSocketSession) {
        try {
            while (true) {
                val frame = session.incoming.receive()
                if (frame is Frame.Text) {
                    val jsonString = frame.readText()

                    // Deserialize the JSON string into a WebSocketMessage
                    val webSocketMessage: WebSocketMessage? = try {
                        Json.decodeFromString<WebSocketMessage>(jsonString)
                    } catch (e: Exception) {
                        Log.e("wslog", "Error parsing JSON", e)
                        null
                    }

                    // handle initial data load vs subsequent updates
                    webSocketMessage?.let { message ->
                        when (message.type) {
                            "initial" -> {
                                message.data?.let { updatedDataset ->
                                    val currentState = _state.value ?: HomeViewState()
                                    _state.postValue(currentState.copy(dataset = updatedDataset))
                                }
                            }
                            "update" -> {
                                message.item?.let { updatedItem ->
                                    val currentState = _state.value ?: HomeViewState()
                                    val updatedDataset = currentState.dataset.toMutableList().apply {
                                        add(updatedItem)
                                    }
                                    _state.postValue(currentState.copy(dataset = updatedDataset))
                                }
                            }
                            else -> {
                                Log.w("wslog", "Unknown message type: ${message.type}")
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.d("wslog", "WebSocket error", e)
            _state.postValue(_state.value?.copy(error = "WebSocket error"))
        }
    }

    // When the viewmodel is being cleared, here making sure to close the socket session manually, and setting it to null in the state.
    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch(Dispatchers.IO) {
            _state.value?.webSocketSession?.close()
            _state.postValue(_state.value?.copy(webSocketSession = null))
        }
    }
}




