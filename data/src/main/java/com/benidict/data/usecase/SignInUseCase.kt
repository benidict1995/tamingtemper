package com.benidict.data.usecase

import com.benidict.data.repository.UserRepository


class SignInUseCase(
    private val userRepository: UserRepository
) {

    suspend fun signIn(username: String, password: String) = userRepository.signIn(username, password)
}