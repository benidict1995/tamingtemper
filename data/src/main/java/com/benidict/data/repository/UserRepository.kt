package com.benidict.data.repository

import com.benidict.domain.model.UserDTO
import com.benidict.network.remote.UserRemoteSource
import com.benidict.persistence.source.UserLocalSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userLocalSource: UserLocalSource,
    private val userRemoteSource: UserRemoteSource
) {
    suspend fun isLoggedIn() = userLocalSource.isLoggedIn()

    suspend fun logOut() = userLocalSource.logOut()

    suspend fun signIn(username: String, password: String): UserDTO {
        val invoke = userRemoteSource.login(
            username = username,
            password = password
        )

        if (invoke.email.isNotEmpty()) {
            userLocalSource.login()
        }
        return invoke
    }
}