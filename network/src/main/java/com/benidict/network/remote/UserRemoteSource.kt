package com.benidict.network.remote

import android.content.Context
import com.benidict.domain.model.UserDTO
import com.benidict.domain.userSource
import com.benidict.domain.utilities.getFileFromAssets
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteSource @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {

    suspend fun loadUserProfile(): UserDTO {
        return withContext(Dispatchers.IO) {
            val filePath = getFileFromAssets(userSource, context)
            val user = gson.fromJson(filePath.readText(), UserDTO::class.java)
            user
        }
    }

    suspend fun login(username: String, password: String): UserDTO {
        return withContext(Dispatchers.IO) {
            val filePath = getFileFromAssets(userSource, context)
            val user = gson.fromJson(filePath.readText(), UserDTO::class.java)
            if (username != user.email && password != user.password)
                UserDTO(
                    name = "",
                    email = "",
                    password = ""
                )
            else user
        }
    }
}