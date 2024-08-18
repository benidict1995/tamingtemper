package com.benidict.network.module

import android.content.Context
import com.benidict.network.remote.ActivitiesRemoteSource
import com.benidict.network.remote.UserRemoteSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {
    @Singleton
    @Provides
    fun providesActivitiesRemoteSource(
        @ApplicationContext appContext: Context,
        gson: Gson
    ) = ActivitiesRemoteSource(
        appContext,
        gson
    )

    @Singleton
    @Provides
    fun providesUserRemoteSource(
        @ApplicationContext appContext: Context,
        gson: Gson
    ) = UserRemoteSource(
        appContext,
        gson
    )
}