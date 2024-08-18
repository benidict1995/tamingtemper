package com.benidict.data.usecase

import com.benidict.data.repository.UserRepository
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke() = userRepository.isLoggedIn()
}