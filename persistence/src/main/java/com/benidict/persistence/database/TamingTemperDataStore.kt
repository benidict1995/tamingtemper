package com.benidict.persistence.database

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TamingTemperDataStore @Inject constructor(
    private val context: Context
) {

    companion object {
        private const val NAME = "tamingtemper_dstore"
        private val Context.dataStore by preferencesDataStore(NAME)
    }

    suspend fun <T> write(
        key: Preferences.Key<T>,
        value: T
    ) {
        context.dataStore.edit {
            it[key] = value
        }
    }

    suspend fun <T> remove(
        key: Preferences.Key<T>
    ) {
        context.dataStore.edit {
            it.remove(key)
        }
    }

    @DelicateCoroutinesApi
    suspend fun <T> getValue(
        key: Preferences.Key<T>
    ): T? {
        try {
            return context.dataStore.data.first()[key]
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }
    }
}