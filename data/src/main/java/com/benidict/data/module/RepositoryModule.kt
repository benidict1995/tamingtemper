package com.benidict.data.module

import com.benidict.data.repository.ActivitiesRepository
import com.benidict.data.repository.UserRepository
import com.benidict.network.remote.ActivitiesRemoteSource
import com.benidict.network.remote.UserRemoteSource
import com.benidict.persistence.source.UserLocalSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesUserRepository(
        userLocalSource: UserLocalSource,
        userRemoteSource: UserRemoteSource
    ) = UserRepository(userLocalSource, userRemoteSource)

    @Provides
    @Singleton
    fun providesActivitiesRepository(
        activitiesRemoteSource: ActivitiesRemoteSource
    ) = ActivitiesRepository(activitiesRemoteSource)
}