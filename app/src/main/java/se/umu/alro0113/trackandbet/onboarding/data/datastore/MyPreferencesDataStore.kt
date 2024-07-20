package se.umu.alro0113.trackandbet.onboarding.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.myPreferencesDataStore: DataStore<Preferences> by preferencesDataStore("settings")

@Singleton
class MyPreferencesDataStore @Inject constructor(
    @ApplicationContext context: Context
) {
    private val myPreferencesDataStore : DataStore<Preferences> = context.myPreferencesDataStore


    private object PreferencesKeys{
        val APP_ENTRY_KEY = booleanPreferencesKey("app_entry")
    }

    val readAppEntry = myPreferencesDataStore.data
        .catch { exception ->
            if (exception is IOException){
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY_KEY] ?: true
        }

    // set to false when user has been on the app the first times
    suspend fun saveAppEntry() {
        myPreferencesDataStore.edit { preferences ->
            preferences[PreferencesKeys.APP_ENTRY_KEY] = false
        }
    }
}





/* Philip Lackner approach with KMP, currently the code below is general
the Android version overloads this function. Keepimg this here to see difference to
implementation above from Compose Campus video

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

// file in filesystem where preferences will be saved, OS dependent
// thus want to abstract away when working with KMP , see vid
fun createDataStore(producePath: () -> String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = { producePath().toPath() }
    )
}

internal const val DATA_STORE_FILE_NAME = "prefs.preferences_pb"

// note that in the video, this file would be a general for all sourcesets
// but since my project is android-only, I will be specializing this file directly
// he instead overloads createDataStore to add the Context.
*/
