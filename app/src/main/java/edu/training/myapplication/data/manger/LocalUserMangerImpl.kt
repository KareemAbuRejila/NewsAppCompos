package edu.training.myapplication.data.manger

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import edu.training.myapplication.domain.manger.LocalUserManger
import edu.training.myapplication.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImpl(
    private val context: Context
):LocalUserManger {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings->
            settings[PreferenceKeys.App_Entry] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferenceKeys.App_Entry]?: false
        }
    }
}

private val readOnlyProperty = preferencesDataStore(name = Constants.USER_SETTINGS)
val Context.dataStore: DataStore<Preferences> by readOnlyProperty

private object PreferenceKeys{
    val App_Entry = booleanPreferencesKey(name = Constants.APP_ENTRY)
}