package com.app.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.app.domain.model.ListArrangement
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private object PreferencesKeys {
        val LIST_ARRANGEMENT = stringPreferencesKey("list_arrangement")
        val USER_TOKEN = stringPreferencesKey("user_token")
    }

    suspend fun updateUserToken(userToken: String) =
        dataStore.edit { prefs -> prefs[PreferencesKeys.USER_TOKEN] = userToken }

    suspend fun getUserToken(): String?  {
        return dataStore.data
            .catch { }
            .map { preferences -> preferences[PreferencesKeys.USER_TOKEN] }
            .first()
    }

    suspend fun updateListArrangement(listArrangement: ListArrangement) =
        dataStore.edit { prefs -> prefs[PreferencesKeys.LIST_ARRANGEMENT] = listArrangement.name }

    suspend fun getListArrangement(): ListArrangement =
        dataStore.data
            .catch { }
            .map { preferences ->
                val listArrangementType = preferences[PreferencesKeys.LIST_ARRANGEMENT]
                val listArrangement =
                    if (listArrangementType != null) { ListArrangement.valueOf(listArrangementType) }
                    else { ListArrangement.List }
                listArrangement
            }
            .first()

}