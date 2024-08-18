package com.benidict.persistence.source

import com.benidict.domain.utilities.IS_LOGGED_IN
import com.benidict.persistence.database.TamingTemperDataStore
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
class UserLocalSource @Inject constructor(
    private val tamingTemperDataStore: TamingTemperDataStore
){
    suspend fun logOut() {
        tamingTemperDataStore.remove(IS_LOGGED_IN)
    }

    suspend fun login(){
        tamingTemperDataStore.write(
            IS_LOGGED_IN, true
        )
    }

    suspend fun isLoggedIn() = tamingTemperDataStore.getValue(IS_LOGGED_IN)


}