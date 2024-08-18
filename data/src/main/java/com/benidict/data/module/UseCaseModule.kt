package com.benidict.data.module

import com.benidict.data.repository.ActivitiesRepository
import com.benidict.data.repository.UserRepository
import com.benidict.data.usecase.IsLoggedInUseCase
import com.benidict.data.usecase.LoadActivitiesUseCase
import com.benidict.data.usecase.LogOutUseCase
import com.benidict.data.usecase.SignInUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun providesLogOut(
        userRepository: UserRepository
    ) = LogOutUseCase(userRepository)

    @ViewModelScoped
    @Provides
    fun providesIsLoggedInUseCase(
        userRepository: UserRepository
    ) =
        IsLoggedInUseCase(userRepository)

    @ViewModelScoped
    @Provides
    fun providesSignInUseCase(
        userRepository: UserRepository
    ) = SignInUseCase(userRepository)

    @ViewModelScoped
    @Provides
    fun provideLoadActivitiesUseCase(
        activitiesRepository: ActivitiesRepository
    ) = LoadActivitiesUseCase(activitiesRepository)
}